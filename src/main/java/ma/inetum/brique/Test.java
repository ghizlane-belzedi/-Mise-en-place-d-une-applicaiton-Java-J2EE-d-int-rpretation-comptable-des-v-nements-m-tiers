package ma.inetum.brique;

/**
 * @author ilyass.asmi
 *
 */
public class Test {

	enum TrafficLight {
		RED, YELLOW, GREEN;
		}
	public static void main(String[] str) {
		System.out.println(TrafficLight.valueOf("RED"));
	}

}
