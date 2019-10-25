package madhu_bussimulation;

public class RandomNumberGenerator {

private static int seed = 1000;	

//Referenced from the lecture - Exponentially Distributed Pseudo- Random Numbers

public static Double random() {
	Double randomNum = - Math.log((seed + 1) / 65536.0); 
	seed = (25173 * seed + 13848) % 65536; 
	return randomNum;
}

}

