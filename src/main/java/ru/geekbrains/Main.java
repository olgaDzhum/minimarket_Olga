package ru.geekbrains;


import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.maven.model.Resource;


import java.io.IOException;
import java.io.InputStream;
@Slf4j
public class Main {

    public static void main(String[] args) throws IOException {
        SqlSessionFactory sqlSessionFactory;
        String resource = "myBatisConfig.xml";
        InputStream is = Resources.getResourceAsStream(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
        SqlSession session = sqlSessionFactory.openSession();

  /*      SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder()
                .build(Resources.getResourceAsStream("myBatisConfig.xml"));
        SqlSession session = sessionFactory.openSession();
        ProductsMapper productsMapper= session.getMapper(ProductsMapper.class);


   */

    }

}

