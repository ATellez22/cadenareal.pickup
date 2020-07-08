package conexion;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

public class Conexion {
	private static BasicDataSource dataSource=null;
	
	private static DataSource getDataSource() {
		if (dataSource==null) {
			dataSource= new BasicDataSource();
			dataSource.setDriverClassName("org.postgresql.Driver");
			dataSource.setUsername("postgres");
			dataSource.setPassword("admin");
			dataSource.setUrl("jdbc:postgresql://localhost:5433/magento");
			dataSource.setInitialSize(20);
			dataSource.setMaxIdle(-15);
			dataSource.setMaxTotal(-10);
			dataSource.setMaxWaitMillis(-1);
		}
		return dataSource;
	}
	
	public static Connection getConnection() throws SQLException {
		return getDataSource().getConnection();
	}
}

/**

El tamaño del grupo inicial de la propiedad especifica el número de conexiones disponibles que se crean cuando la agrupación de conexiones es inicialmente creada
 o se reinicializa. Esta propiedad se utiliza normalmente para reducir el tiempo de arranque necesario para el purgado del pool a su tamaño óptimo.

Un valor de cero indica que no hay conexiones creadas previamente. El valor predeterminado es cero. El siguiente ejemplo muestra la configuración
 de un tamaño de grupo inicial:

pds.setInitialPoolSize(5);

Ajuste el tamaño mínimo del pool
El tamaño mínimo del pool especifica la cantidad mínima de las conexiones disponibles y prestadas que un grupo mantiene. 
Un grupo de conexiones siempre trata de volver al tamaño mínimo especificado a menos que la cantidad mínima aún no se ha alcanzado. Por ejemplo, si el límite mínimo
 se establece en diez y sólo dos conexiones se han creado y prestado, entonces el número de conexiones que mantiene el pool permanece en dos.

Esta propiedad permite que el número de conexiones del pool disminuya a medida que disminuye la demanda. Al mismo tiempo, la propiedad se asegura de que los recursos 
del sistema no se desperdician en el mantenimiento de las conexiones que son innecesarias. El valor predeterminado es cero. El siguiente ejemplo muestra la configuración
 de un pool de tamaño mínimo:

pds.setMinPoolSize(2);

Establecer el tamaño máximo de Pool
El tamaño máximo especifica el número máximo de las conexiones que mantiene un pool. Si se toman prestadas el número máximo de conexiones, las conexiones no estarán 
disponibles hasta que una conexión se devuelve al pool.

Esta propiedad permite que el número de conexiones en el pool aumente a medida que aumenta la demanda. Al mismo tiempo, la propiedad asegura que el pool no crezca hasta 
el punto de agotar los recursos de un sistema, que en última instancia, afecta al rendimiento de una aplicación y disponibilidad.

Un valor de cero indica que no hay conexiones gestionada por el pool. Un intento de obtener un resultado de conexión en esta situación, es una excepción. 
El siguiente ejemplo muestra la configuración del tamaño máximo del grupo:

pds.setMaxPoolSize(100);

**/