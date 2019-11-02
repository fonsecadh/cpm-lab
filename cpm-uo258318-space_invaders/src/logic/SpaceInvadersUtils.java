package logic;

public class SpaceInvadersUtils {
	
	public static boolean isNumeric(String str) {
		return str.chars().allMatch(c -> Character.isDigit(c));
	}

}
