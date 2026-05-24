package fiap._tdspo.clyvoHelper.clyvoHelper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ClyvoHelperApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClyvoHelperApplication.class, args);
	}

}
