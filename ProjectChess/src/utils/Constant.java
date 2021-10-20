package utils;

public class Constant {
	public final static int BOARD_LENGTH = 8;
	public final static int UNIT_CODE_LENGTH = 2;
	public final static int LOCATION_CODE_LENGTH = 2;
	public final static int ROOK_DIRECTION_MAX = 4;
	public final static int KNIGHT_DIRECTION_MAX = 8;
	public final static int BISHOP_DIRECTION_MAX = 4;
	public final static int QUEEN_DIRECTION_MAX = 8;
	public final static int KING_DIRECTION_MAX = 8;
	
	public final static int BUTTON_ZERO = 0;
	public final static int BUTTON_ONE = 1;
	public final static int BUTTON_TWO = 2;
	
	public final static int TURN_INITIAL_COUNT = 0;
	
	public final static String PLAYER_COLOR_BLACK = "Black";
	public final static String PLAYER_COLOR_WHITE = "White";
	
	public final static String KING_NAME = "K0";
	public final static String QUEEN_NAME = "Q0";

	public final static String PAWN_1_NAME = "P1";
	public final static String PAWN_2_NAME = "P2";
	public final static String PAWN_3_NAME = "P3";
	public final static String PAWN_4_NAME = "P4";
	public final static String PAWN_5_NAME = "P5";
	public final static String PAWN_6_NAME = "P6";
	public final static String PAWN_7_NAME = "P7";
	public final static String PAWN_8_NAME = "P8";

	public final static String KNIGHT_1_NAME = "N1";
	public final static String KNIGHT_2_NAME = "N2";

	public final static String BISHOP_1_NAME = "B1";
	public final static String BISHOP_2_NAME = "B2";

	public final static String ROOK_1_NAME = "R1";
	public final static String ROOK_2_NAME = "R2";
	
	public final static String MAIN_MENU_HEADER = "===== Chess =====";
	public final static String MAIN_MENU_SEPARATOR = "=================";
	public final static String MAIN_MENU_PLAYGAME = "1. Play New Game";
	public final static String MAIN_MENU_LOADGAME = "2. Load Game";
	public final static String MAIN_MENU_EXIT = "0. Exit";
	
	public final static String MAIN_MENU_EXIT_MESSAGE = "[Message] Thank you for playing!";
	
	public final static String GAME_MENU_SEPARATOR = "===================================================================";
	
	public final static String PLAYER_WHITE_VICTORY = "[Message] Player White Wins!";
	public final static String PLAYER_BLACK_VICTORY = "[Message] Player Black Wins!";
	public final static String PLAYER_WHITE_TURN = "[--------------- White Turn ---------------]";
	public final static String PLAYER_BLACK_TURN = "[--------------- Black Turn ---------------]";
	
	public final static String ILLEGAL_ARGUMENT_EXCEPTION_INPUT_LENGTH = "[Message] Unit code should only have one letter and a number";
	public final static String ILLEGAL_ARGUMENT_EXCEPTION_INPUT_FORM = "[Message] Unit code form should only be comprised of one letter + one number";
	public final static String ILLEGAL_ARGUMENT_EXCEPTION_INPUT_LETTER_RANGE = "[Message] A letter in the unit code should have a range of \'a\' to \'h\'";
	public final static String ILLEGAL_ARGUMENT_EXCEPTION_INPUT_NUMBER_RANGE = "[Message] A number in the unit code should have a range of 1 to 8";
	
	public final static String CHOOSE_UNIT_MESSAGE = "[Message] Choose a piece";
	public final static String CHOOSE_LOCATION_MESSAGE = "[Message] Choose a tile";
	public final static String CHOOSE_PAWN_PROMOTION_MESSAGE = "[Message] Choose a piece for promotion.";
	
	public final static String PLAYER_CHECK_OPPONENT_KING_MESSAGE = "[Message] !!!!!CHECK!!!!!";
	
	public final static String GAME_TURN_COUNT_HEADER = "* Turn : ";
	
}
