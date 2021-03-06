package ol.pokwebservice.objects;

import java.nio.charset.Charset;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.NoArgsConstructor;
import ol.pokwebservice.utils.AllUtils;

@Entity
@Data
public class ApiAccount {
	
	@Id
	private String id;
	private String dateAppelApi;
	private int nbAppelApi;
	@OneToMany
	List<Carte> cartesCourantes;
	
	public ApiAccount() {
		super();
		this.id = generateRandomId();
	}
	
	public String generateRandomId() {
		int leftLimit = 97; // letter 'a'
	    int rightLimit = 122; // letter 'z'
	    int targetStringLength = AllUtils.getRandomNumberInRange(10, 30);
	    Random random = new Random();
	    StringBuilder buffer = new StringBuilder(targetStringLength);
	    for (int i = 0; i < targetStringLength; i++) {
	        int randomLimitedInt = leftLimit + (int) 
	          (random.nextFloat() * (rightLimit - leftLimit + 1));
	        buffer.append((char) randomLimitedInt);
	    }
	    String generatedString = buffer.toString();
	 
	    return generatedString;
	}

	public ApiAccount(String id) {
		super();
		this.id = id;
	}

}
