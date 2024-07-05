# Documentação math-api

- Está API futuramente será chamada em outro projeto, que rodará localmente.
- Este outro projeto será um aplicativo mobile Flutter, o qual o mesmo irá se comunidar com está API, ela por sua vez se comunicará com um banco de dados local ou em memória e vai responder a essas comunicações depende do perfil que estiver utilizando

## Lista de tasks

- [ ] Desenvolver a API
  - [x] Configurar projeto Spring Boot
  - [x] Configurar JPA
  - [x] Configurar H2
  - [x] Desenvolver exception para ResourceNotFound
  - [x] Desenvolver handler para ResourceNotFound
  - [ ] Implementar sistemática do Spring Security com JWT
  - [ ] Configurar perfis de desenvolvimento
    - [x] Perfil de teste
    - [ ] Perfil de dev
    - [ ] Perfil de prod
- [ ] Desenvolver o App
- [ ] Estrutura banco de dados em memória (H2)
- [ ] Estruturar banco de dados local (PostgreSQL)

## Endpoint desenvolvidos

- Url base para teste da api: http://localhost:8080
- Url para requisições estando na mesma rede local: IP da máquina:8080
  - Podemos verificar rodando o comando `ipconfig` no cmd, é o endereço IPv4

| Endpoint         | Tipo | Descrição                                                                      |
| ---------------- | :--: | ------------------------------------------------------------------------------ |
| `/usuarios`      | GET  | Retorna todos os usuário que tem no banco                                      |
| `/usuarios/{id}` | GET  | Retorna um usuário ou uma exception ResourceNotFound                           |
| `/produtos`      | GET  | Retorna todos os produtos que tem no banco                                     |
| `/produtos/{id}` | GET  | Retorna um produto ou uma exception ResourceNotFound                           |
| `/produtos`      | POST | Necessário passar no body, um novo produto, será retornado no header o caminho |

## Como implementar o Spring Security

1. Primeiro devemos criar um arquivo de configuração, para desabilitar as configurações padrões do Spring Security, pois ao adicionar a dependência ao `pom.xml`, o Spring Security, já terá configurações padrões, podemos visualizar esse arquivo em:

   ```
   .
   ├── HELP.md
   ├── mvnw
   ├── mvnw.cmd
   ├── pom.xml
   ├── README.md
   ├── src
   |  ├── main
   |  |  ├── java
   |  |  |  ├── br
   |  |  |  |  └── com
   |  |  |  |     └── matheus
   |  |  |  |        └── mathapi
   |  |  |  |           ├── domain
   |  |  |  |           |  └── ...
   |  |  |  |           ├── MathApiApplication.java
   |  |  |  |           ├── repositories
   |  |  |  |           |  └── ...
   |  |  |  |           ├── resources
   |  |  |  |           |  └── ...
   |  |  |  |           ├── security
   |  |  |  |           |  └── SecurityConfiguration.java <- Aqui se encontra o arquivo de configuração
   |  |  |  |           └── services
   |  |  |  |              └── ...
   |  |  └── resources
   |  |     └── ...
   |  └── test
   |     └── ...
   └── target
   └── ...
   ```

2. Em seguida adicionar as anotações `@Configuration` e `@EnableWebSecurity`, em seguida adicionamos um método como este:

   ```java
   @Configuration
   @EnableWebSecurity
   public class SecurityConfiguration {

       @Bean
       SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
           return httpSecurity.csrf(csrf -> csrf.disable())
                   .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).build();
       }

   }
   ```

   - Importante ressaltar que precisamos adicionar a anotação `@Bean` nesse método, para sinalizar ao spring que se trata de um método que retorna um "Bean" que pode ser usado como configuração pelo Spring.

3. Já na nossa classe de domain para usuários, que será quem devera ser validado, devemos implementar a interface `UserDetails` (além da `Serializable`) e implementar seus métodos (No exemplo que segui, apenas vamos configurar o `getAuthorities()`, os restantes pode retornar sempre true).

   ```java
   @Entity
   @Getter
   @NoArgsConstructor
   @AllArgsConstructor
   @EqualsAndHashCode(of = "id")
   public class Usuario implements UserDetails, Serializable {

       private static final long serialVersionUID = 1L;

       @Id
       @GeneratedValue(strategy = GenerationType.IDENTITY)
       private Long id;
       @NotBlank
       private String login;
       @NotBlank
       private String password;
       @NotBlank
       private int role;

       @Override
       public Collection<? extends GrantedAuthority> getAuthorities() {
           if (this.role == UsuarioRole.ADMIN.getRole())
               return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
           else
               return List.of(new SimpleGrantedAuthority("ROLE_USER"));
       }

       @Override
       public String getUsername() {
           return login;
       }

       @Override
       public String getPassword() {
           return password;
       }

       @Override
       public boolean isAccountNonExpired() {
           return true;
       }

       @Override
       public boolean isAccountNonLocked() {
           return true;
       }

       @Override
       public boolean isCredentialsNonExpired() {
           return true;
       }

       @Override
       public boolean isEnabled() {
           return true;
       }

   }
   ```

   - Aqui vamos utilizar o atributo `int role` para fazer a validação do usuário, verificando suas roles, para permitir seus acessos a determinados endpoint ou não, aqui fiz alguns ajustes para conseguir utilizar juntamente com enums

4. Em seguida devemos adicionar um método ao nosso repository que retornará um `UserDetails`, ficando desta forma:

   ```java
   @Repository
   public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

       UserDetails findByLogin(String login);

   }
   ```

5. Depois é necessário implementar a interface `UserDetailsService` no nosso Service, retornando nosso método que adicionamos no repository:

   ```java
   @Service
   public class UsuarioService implements UserDetailsService{

       @Autowired
       private UsuarioRepository repository;

       @Override
       public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
           return repository.findByLogin(username);
       }

       public List<Usuario> getAllUsuario() {
           return repository.findAll();
       }

       public Usuario getById(Long id) {
           Usuario usuario = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
           return usuario;
       }

   }
   ```
