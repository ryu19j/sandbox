package example;

import java.util.Base64;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		System.out.println("Hello World!");

		String str = Base64.getEncoder().encodeToString("Administrator:cybozu".getBytes());
		System.out.println(str);
		System.out.println("QWRtaW5pc3RyYXRvcjpjeWJvenU=");
		String str2 = org.glassfish.jersey.internal.util.Base64.encodeAsString("Administrator:cybozu");
		System.out.println(str2);

		String str3 = new String(Base64.getDecoder().decode("QWRtaW5pc3RyYXRvcjpjeWJvenU="));
		System.out.println(str3);
	}
}
