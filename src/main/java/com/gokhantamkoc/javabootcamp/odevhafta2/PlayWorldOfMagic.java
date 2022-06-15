package com.gokhantamkoc.javabootcamp.odevhafta2;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class PlayWorldOfMagic implements CommandLineRunner {
	public static void main(String[] args) {
		SpringApplication.run(PlayWorldOfMagic.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		int maxNumOfAttacksAllowed = 9;
		String[] magicianSpells = createSpellNameRepository();
		float[] spellDamageInfo = createSpellDamageRepository();
		String[] bossNames = createBossNameRepository();
		float[] bossHps = createBossHPRepository();
		
		int minNumberSpellsUsed = resolveBattle(
				magicianSpells, 
				spellDamageInfo, 
				bossNames, 
				bossHps);
		
		if (minNumberSpellsUsed > maxNumOfAttacksAllowed) {
			System.out.println("Magician died!");
		} else if (minNumberSpellsUsed > 0 && minNumberSpellsUsed <= maxNumOfAttacksAllowed) {
			System.out.println("Magician won the battle!");
		} else {
			System.out.println("Result is not correct!");
		}
	}
	
	public static int resolveBattle(
			String[] magicianSpells,
			float[] spellDamageInfo,
			String[] bossNames,
			float[] bossHPs
			) {

		int spellsUsed = 0;
		// ______ BASLANGIC _______ Kodunuz buradan baslamali

		//Bu kisimda kullanacagim degiskenleri tanimladim.
		String mainSkill="";
		float mainDamage=0;
		int round=1;

		//Bu dongude spellDamage listesinden hasari en yuksek olan spell'i sectim.
		for (int i = 0; i < spellDamageInfo.length ; i++) {
			if (mainDamage < spellDamageInfo[i]){
				mainDamage = spellDamageInfo[i];
				mainSkill = magicianSpells[i];
			}
		}


		//Bu dongude bossHps listesinde bulunan boss Hp'lerini gezdim.
		for (int i = 0; i <bossHPs.length; i++) {

			//while kullanarak bossHps listesinde boss canlari 0'a inene kadar devam etmesini sagladim.
			while (bossHPs[i]>0){
				System.out.println(round +". round -> " + "Magician vs " + bossNames[i] + "\nUsed Spell -> " + mainSkill);

				//Her bir index'te bulunan boss Hp'lerini vurulan hasardan cıkartarak yeni canlarini belirledim.
				bossHPs[i]-=mainDamage;

				//1 tur gectigi icin round ve spellsUsed 1 arttirdim her tur dondugunde 1 artacak
				spellsUsed++;
				round++;

				//if-else kisminda ise boss'un olup olmedigi kosullarina göre ekrana cikti aldim.
				if (bossHPs[i]>0){
					System.out.println("Boss " + bossNames[i] + " Damage Given : " + mainDamage + " Remain HP : " + bossHPs[i]+ "\n");
				}else
					System.out.println(bossNames[i]  + " is dead"+ "\n");
			}

		}
		System.out.println("Number of used spells = " + spellsUsed+ "\n");


		// ______ SON _______ Kodunuz burada bitmeli
		/* NOT: ______ BASLANGIC _______ ve ______ SON _______ 
		 * arasina istediginiz kadar sayida satir ekleyebilirsiniz.
		 */
		return spellsUsed;
	}
	
	public static String[] createSpellNameRepository() {
		return new String[]{"Ice Storm", "Chain Lightning", "Magic Missile"};
	}
	
	public static float[] createSpellDamageRepository() {
		return new float[]{40.0f, 30.0f, 5.0f};
	}
	
	public static String[] createBossNameRepository() {
		return new String[]{"Dire Rat", "Skeleton Knight", "Undead King"};
	}
	
	public static float[] createBossHPRepository() {
		return new float[]{15.0f, 45.0f, 60.0f};
	}
}
