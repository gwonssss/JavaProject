public class WithPlayer {
//플레이어 1, 2의 코인 
//플레이어 턴
//뒤로가기 버튼 클릭 시 플레이어들의 코인 리셋
//1 : 가위 		2 : 바위		 3 : 보
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
	
	public void setPlayerChoose(int rsp, int betCoin) {// 가위바위보값, 배팅한 코인 값
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
				betResult = "\n1P 배팅한 코인 : " + betCoin + "\n1P 남은 코인 : " + player1CoinCount; 
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
				betResult = "\n2P 배팅한 코인 : " + betCoin + "\n2P 남은 코인 : " + player2CoinCount;
			}
			//System.out.println("2번 선택 : " + rsp + "\n배팅한 코인 :" + betCoin + "\n남은 코인 : "+ player2CoinCount);
			vsPlayerGameFunction();
		}
	}
	
	public void vsPlayerGameFunction() {
		//1 : 가위 		2 : 바위		 3 : 보
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
		winLog = "\n비겼습니다. 배팅한 코인을 돌려 받습니다.\n 1P남은 코인 : " + player1CoinCount
				+ "\n 2P남은 코인 : " + player2CoinCount;
	}
	public void player1Win() {
		player1CoinCount += player1betCoin + player2betCoin;
		winLog = "\n1P WIN! 2P가 배팅한 코인을 가져옵니다.\n 1P남은코인 : " + player1CoinCount
				+ "\n 2P남은코인 : " + player2CoinCount;
		playerBankrupt();
		
	}
	public void player2Win() {
		player2CoinCount += player1betCoin + player2betCoin;
		
		winLog = "\n2P WIN! 1P가 배팅한 코인을 가져옵니다.\n 1P남은코인 : " + player1CoinCount
				+ "\n 2P남은코인 : " + player2CoinCount;
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
