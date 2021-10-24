package utils;

public class Constant {
	public final static int BOARD_LENGTH = 8;
	public final static int UNIT_CODE_LENGTH = 2;
	public final static int LOCATION_CODE_LENGTH = 2;
	
	public final static int PAWN_DIRECTION_MAX = 4;
	public final static int ROOK_DIRECTION_MAX = 4;
	public final static int KNIGHT_DIRECTION_MAX = 8;
	public final static int BISHOP_DIRECTION_MAX = 4;
	public final static int QUEEN_DIRECTION_MAX = 8;
	public final static int KING_DIRECTION_MAX = 8;
	
	public final static int[] WHITE_PAWN_RANGE_DY = {-2,-1,-1,-1};
	public final static int[] WHITE_PAWN_RANGE_DX = {0,-1,0,-1};
	public final static int[] BLACK_PAWN_RANGE_DY = {2,1,1,1};
	public final static int[] BLACK_PAWN_RANGE_DX = {0,-1,0,-1};
	
	public final static int[] ROOK_MOVE_RANGE_DY = {-1,0,1,0};
	public final static int[] ROOK_MOVE_RANGE_DX = {0,1,0,-1};
	
	public final static int[] BISHOP_MOVE_RANGE_DY = {-1,-1,1,1};
	public final static int[] BISHOP_MOVE_RANGE_DX = {-1,1,1,-1};
	
	public final static int[] KNIGHT_MOVE_RANGE_DY = {-2,-2,-1,1,2,2,1,-1};
	public final static int[] KNIGHT_MOVE_RANGE_DX = {-1,1,2,2,1,-1,-2,-2};

	public final static int[] QUEEN_MOVE_RANGE_DY = {-1,0,1,0,-1,-1,1,1};
	public final static int[] QUEEN_MOVE_RANGE_DX = {0,1,0,-1,-1,1,1,-1};
	
	public final static int[] KING_MOVE_RANGE_DY = {-1,0,1,0,-1,-1,1,1};
	public final static int[] KING_MOVE_RANGE_DX = {0,1,0,-1,-1,1,1,-1};
	
	public final static int BUTTON_ZERO = 0;
	public final static int BUTTON_ONE = 1;
	
	public final static int TURN_INITIAL_COUNT = 0;
	
	public final static String PLAYER_COLOR_BLACK = "Black";
	public final static String PLAYER_COLOR_WHITE = "White";
	
	public final static String ANSWER_YES = "Y";
	public final static String ANSWER_NO = "N";
	public final static String KING_SIDE = "K";
	public final static String QUEEN_SIDE = "Q";
	
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
	public final static String MAIN_MENU_EXIT = "0. Exit";
	public final static String MAIN_MENU_EXIT_MESSAGE = "[Message] Thank you for playing!";
	
	public final static String GAME_MENU_SEPARATOR = "===================================================================";
	public final static String GAME_TURN_COUNT_HEADER = "* Turn : ";
	
	public final static String PLAYER_WHITE_VICTORY = "[Message] Player White Wins!";
	public final static String PLAYER_BLACK_VICTORY = "[Message] Player Black Wins!";
	public final static String PLAYER_WHITE_TURN = "[--------------- White Turn ---------------]";
	public final static String PLAYER_BLACK_TURN = "[--------------- Black Turn ---------------]";
	
	public final static String ILLEGAL_ARGUMENT_EXCEPTION_UNITCODE_LENGTH = "[Error Message] Unit code should only have one letter and a number.";
	public final static String ILLEGAL_ARGUMENT_EXCEPTION_UNITCODE_FORM = "[Error Message] Unit code should only be comprised of one upper case letter + one number.";
	public final static String ILLEGAL_ARGUMENT_EXCEPTION_UNITCODE_UPPERCASE = "[Error Message] First letter in the unit code should be an upper case letter.";
	public final static String ILLEGAL_ARGUMENT_EXCEPTION_UNITCODE_INVALID_UNITCODE = "[Error Message] Invalid unit code";
	public final static String ILLEGAL_ARGUMENT_EXCEPTION_UNITCODE_INVALID_PROMOTION = "[Error Message] You can only promote your Pawn to -> Queen(Q) Bishop(B) Knight(N) Rook(R)";
	
	public final static String ILLEGAL_ARGUMENT_EXCEPTION_LOCATION_LENGTH = "[Error Message] Location code should only have one letter and a number.";
	public final static String ILLEGAL_ARGUMENT_EXCEPTION_LOCATION_FORM = "[Error Message] Location code should only be comprised of one lower case letter + one number.";
	public final static String ILLEGAL_ARGUMENT_EXCEPTION_LOCATION_LOWERCASE = "[Error Message] First letter in the location code should be a lower case letter.";
	public final static String ILLEGAL_ARGUMENT_EXCEPTION_LOCATION_LETTER_RANGE = "[Error Message] A letter in the location code should be within the range of \'a\' to \'h\'.";
	public final static String ILLEGAL_ARGUMENT_EXCEPTION_LOCATION_NUMBER_RANGE = "[Error Message] A number in the location code should be within the range of 1 to 8.";
	
	public final static String ILLEGAL_ARGUMENT_EXCPETION_MOVE_RANGE1 = "[Error Message] Move coordinates should be within the range of 0 - 7.";
	public final static String ILLEGAL_ARGUMENT_EXCPETION_MOVE_RANGE2 = "[Error Message] Cannot choose current coordinates of your chosen piece.";
	public final static String ILLEGAL_ARGUMENT_EXCEPTION_MOVE_SAME_TEAM = "[Error Message] Cannot move to a location occupied by your own piece.";
	public final static String ILLEGAL_ARGUMENT_EXCEPTION_MOVE_UNIT_SPECIFIC = "[Error Message] Invalid move for the chosen piece.";
	
	public final static String ILLEGAL_ARGUMENT_EXCEPTION_CASTLING_ANSWER = "[Error Message] Invalid Answer. Please input \'Y\' or \'N\'";
	public final static String ILLEGAL_ARGUMENT_EXCEPTION_CASTLING_SIDE = "[Error Message] Invalid Answer. Please input \'K\' or \'Q\'";

	public final static String ILLEGAL_ARGUMENT_EXCEPTION_CASTLING_MOVED = "[Error Message] Once either your king or rook moves, you can no longer castle.";
	public final static String ILLEGAL_ARGUMENT_EXCEPTION_CASTLING_CHECK_ON_KING = "[Error Message] Your king can NOT be in check";
	public final static String ILLEGAL_ARGUMENT_EXCEPTION_CASTLING_BETWEEN_KING_ROOK = "[Error Message] No pieces can be between the king and rook.";
	public final static String ILLEGAL_ARGUMENT_EXCEPTION_CASTLING_PASS_CHECK = "[Error Message] Your king can not pass through check";
	
	public final static String CHOOSE_UNIT_MESSAGE = "[Message] Choose a piece";
	public final static String CHOOSE_LOCATION_MESSAGE = "[Message] Choose a tile";
	public final static String CHOOSE_PAWN_PROMOTION_MESSAGE = "[Message] Choose a piece for promotion -> Q , B , N , R";
	public final static String CHOOSE_CASTLING_SIDE_MESSAGE = "[Message] King Side Castling OR Queen Side Castling? (K/Q)";
	public final static String CHOOSE_DO_OR_NOT_DO_CASTLING_MESSAGE = "[Message] Castle your king? (Y/N)";
	
	public final static String PLAYER_CHECK_OPPONENT_KING_MESSAGE = "[Message] !!!!!CHECK!!!!!";
}
