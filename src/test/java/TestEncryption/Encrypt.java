package TestEncryption;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import Encrypt.Encryption;


class Encrypt {

	@Test
	void test1() {
		Assertions.assertEquals(Encryption.encrypt("salut"), "ckved");
		System.out.println(Encryption.encrypt("salut"));
	}
	
	@Test
	void test2() {
		Assertions.assertEquals(Encryption.encrypt("info.uvt.ro"), "sxpyRefdRby");
		System.out.println(Encryption.encrypt("info.uvt.ro"));
	}
	
	@Test
	void test3() {
		Assertions.assertEquals(Encryption.encrypt("programare3"), "zbyqbkwkboW");
		System.out.println(Encryption.encrypt("programare3"));
	}
	
	@Test
	void test4() {
		Assertions.assertEquals(Encryption.encrypt("Proiect_Java_9"), "ZbysomdiTkfki]");
		System.out.println(Encryption.encrypt("Proiect_Java_9"));
	}
	
	@Test
	void test5() {
		Assertions.assertEquals(Encryption.encrypt("UVT_FMI_II_2025"), "_`^iPWSiSSiVTVY");
		System.out.println(Encryption.encrypt("UVT_FMI_II_2025"));
	}
}
