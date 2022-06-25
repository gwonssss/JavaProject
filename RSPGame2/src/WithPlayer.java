public class WithPlayer {
//�÷��̾� 1, 2�� ���� 
//�÷��̾� ��
//�ڷΰ��� ��ư Ŭ�� �� �÷��̾���� ���� ����
//1 : ���� 		2 : ����		 3 : ��
	private int player1Choose = 0;
	private int player2Choose = 0;
	private int player1betCoin;
	private int player2betCoin;
	private int startCoin = 25;

	private int player1CoinCount = startCoin;
	private int player2CoinCount = startCoin;
	private int playerTurn = 1;
	private int errorCheck;
	
	
	String betResult;
	String winLog;
	
	public void setPlayerChoose(int rsp, int betCoin) {// ������������, ������ ���� ��
		playerTurn++;
		if(playerTurn%2 == 0) {
			player1Choose = rsp;
			player1betCoin = betCoin;
			
			if(player1betCoin > player1CoinCount) {
				RSPFrame.errorDialog();
				errorCheck = 1;
				playerTurn = 1;
			}else {
				player1CoinCount -= betCoin;
				errorCheck = 0;
				betResult = "\n1P ������ ���� : " + betCoin + "\n1P ���� ���� : " + player1CoinCount; 
			}
		}
		else if(playerTurn%2 > 0) {
			player2Choose = rsp;
			player2betCoin = betCoin;
			
			if(player2betCoin > player2CoinCount) {
				RSPFrame.errorDialog();
				errorCheck = 1;
				playerTurn = 0;
			}
			else {
				errorCheck = 0;
				player2CoinCount -= betCoin;
				betResult = "\n2P ������ ���� : " + betCoin + "\n2P ���� ���� : " + player2CoinCount;
			}
			//System.out.println("2�� ���� : " + rsp + "\n������ ���� :" + betCoin + "\n���� ���� : "+ player2CoinCount);
			vsPlayerGameFunction();
		}
	}
	
	public void vsPlayerGameFunction() {
		//1 : ���� 		2 : ����		 3 : ��
		if(playerTurn%2 > 0) {
			if(player1Choose == 1) {
				if(player2Choose == 1) {
					draw();
				}else if(player2Choose == 2) {
					player2Win();
				}else if(player2Choose == 3) {
					player1Win();
				}
			}else if(player1Choose == 2) {
				if(player2Choose == 1 ) {
					player1Win();
				}else if(player2Choose == 2 ) {
					draw();
				}else if(player2Choose == 3) {
					player2Win();
				}
			}else if(player1Choose == 3) {
				if(player2Choose == 1) {
					player2Win();
				}else if(player2Choose == 2) {
					player1Win();
				}else if(player2Choose == 3) {
					draw();
				}
			}
		}
	}
	public void draw() {
		player1CoinCount += player1betCoin;
		player2CoinCount += player2betCoin;
		winLog = "\n�����ϴ�. ������ ������ ���� �޽��ϴ�.\n 1P���� ���� : " + player1CoinCount
				+ "\n 2P���� ���� : " + player2CoinCount;
	}
	public void player1Win() {
		player1CoinCount += player1betCoin + player2betCoin;
		winLog = "\n1P WIN! 2P�� ������ ������ �����ɴϴ�.\n 1P�������� : " + player1CoinCount
				+ "\n 2P�������� : " + player2CoinCount;
		playerBankrupt();
		
	}
	public void player2Win() {
		player2CoinCount += player1betCoin + player2betCoin;
		
		winLog = "\n2P WIN! 1P�� ������ ������ �����ɴϴ�.\n 1P�������� : " + player1CoinCount
				+ "\n 2P�������� : " + player2CoinCount;
		playerBankrupt();
		
	}
	
	public void pageCount(int pageCount) {
		if(pageCount == 1) {
			playerTurn = 1;
			resetPlayer();
		}
	}
	public void resetPlayer() {
		player1CoinCount = startCoin;
		player2CoinCount = startCoin;
		playerTurn = 1;
	}
	public void playerBankrupt() {
		if(player1CoinCount == 0) {
			RSPFrame.player1bankruptDialog();
		}else if(player2CoinCount == 0) {
			RSPFrame.player2bankruptDialog();
		}
	}
	public String getBetLog() {
		return betResult;
	}
	public int getError() {
		return errorCheck;
	}
	public int getPlayerTurn() {
		return playerTurn;
	}
	public String getWinLog() {
		return winLog;
	}
}
