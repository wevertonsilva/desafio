Passos para rodar a aplicação com banco local (H2)

OBS.: Para garantir que está usando a ultima versão do cógigo, favor usar a branch develop

1. Configurar o H2 no seu application.properties
```
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.username=admin
spring.datasource.password=
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
#spring.datasource.url=jdbc:mysql://localhost:3306/test?useTimezone=true&serverTimezone=America/Sao_Paulo
#spring.datasource.username=root
#spring.datasource.driverClassName=com.mysql.jdbc.Driver
#spring.jpa.show-sql=true
#spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL5Dialect
```

4. Após subir a aplicação, a documentação se encontrará no seguinte link: http://localhost:8080/swagger-ui/index.html#/
