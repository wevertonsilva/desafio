Passos para rodar a aplicação com banco local (H2)

OBS.: É necessário executar a criação de um novo usuário e realizar o login para gerar o token do usuário. Este token deve ser usado para o envio de requisições (com exceção das rotas de usuário).

1. Configurar o H2 no seu application.properties
```
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
spring.datasource.url=<url do seu banco>
spring.datasource.username=<usuario do seu banco>
spring.datasource.password=<senha do seu banco>
spring.datasource.driver-class-name=org.h2.Driver
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
```

2. Configurar a propriedade PathPattern para rodar o swagger
```
spring.mvc.pathmatch.matching-strategy=ant-path-matcher
```

3. Subir a aplicação

OBS.: É necessário rodar o script <b>"tables.sql"</b> que se encontra no pacote "scripts".

OBS.: Também é possível rodar a aplicação com MySQL, bastar todar o script comentado acima no seu
banco de dados e adicionar a configuração do MySQL no seu application.properties, segue exemplo:
```
#spring.datasource.url=<url do seu banco>
#spring.datasource.username=<usuario do seu banco>
spring.datasource.password=<senha do seu banco>
#spring.datasource.driverClassName=com.mysql.jdbc.Driver
#spring.jpa.show-sql=true
#spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL5Dialect
```

4. Após subir a aplicação, a documentação se encontrará no seguinte link: http://localhost:8080/swagger-ui/index.html#/
