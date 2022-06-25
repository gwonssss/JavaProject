public class WithComputer {
//버튼클릭 시 RSP와 bettingDialog에서 입력하는 betCoin 받아옴
//1 : 가위 		2 : 바위		 3 : 보
//COMPUTER의 RSP값은 Math.random()으로 랜덤하게 생성
//COMPUTER의 RSP값에 맞춰서 낸 값이 무엇인지 winLog로 출력
//COMPUTER의 RSP값을 computerRSPLog로 바꿔서 winLog에 삽입 후 출력
	//스위치케이스문 적용
//Player 3연승 시 3연승 보너스 로그가 뜨면서 코인+
	//연승 메소드
//3연승이 아닐 시에는 이겼을 때 2배, 비겼을 때 0, 졌을 시 배팅한 코인 소멸
//코인의 수가 100이되면 게임 승리, Player의 코인이 0이 되면 게임 패배(winDialog, LoseDialog)


	private int playerChoose = 0;
	private int computerChoose;
	private int startCoin = 50;
	private int playerCoinCount = startCoin;
	private int errorCheck;
	private int winCount = 0;
	private String betResult;
	private String winLog;
	private String computerRSPLog;
	private String playerRSPLog;
	private int playerBetCoin;
	private String winBonusLog;
	private int bonusCheck;
	
	
	public void settingPlayer(int rsp, int betCoin) {
		playerChoose = rsp;
		playerBetCoin = betCoin;
		
		if(playerBetCoin > playerCoinCount) {
			RSPFrame.errorDialog();
			errorCheck = 1;
		}else {
			playerCoinCount = playerCoinCount - betCoin;
			errorCheck = 0;
			betResult = "\n배팅한 코인 : " + playerBetCoin + "\n남은 코인 : " + playerCoinCount; 
			playerRSP();
			settingComputer();
		}
		vsComputerGameFunction();
	}
	public void settingComputer() {
		computerChoose = (int)(Math.random()*3+1);
		computerRSP();
	}
	public void vsComputerGameFunction() {
		int vsComputerGameResult = playerChoose - computerChoose;
		
		if(vsComputerGameResult == -2 || vsComputerGameResult == 1) {
			win();
		}else if(vsComputerGameResult == 0) {
			draw();
		}else {
			lose();
		}
	}
	public void win(){
		playerCoinCount += playerBetCoin * 2;
		winCount += 1;
		winningBonus();
		winLog = "\nplayer가 이겼습니다. 배팅 코인에 2배를 가져옵니다. \n남은 코인 : " + playerCoinCount+ "\n";
		if(playerCoinCount >= 100) {
		  RSPFrame.playerWinDialog();
		}
	}
	public void draw() {
		playerCoinCount += playerBetCoin;
		winCount = 0;
		bonusCheck = 0;
		winLog = "\n비겼습니다. 배팅한 코인이 되돌아옵니다. \n남은 코인 : " + playerCoinCount + "\n";
	}
	public void lose() {
		winCount = 0;
		bonusCheck = 0;
		winLog = "\ncomputer가 이겼습니다. 코인이 줄어듭니다. \n남은 코인 : " + playerCoinCount+ "\n";
		if(playerCoinCount <= 0) {
		 RSPFrame.playerLoseDialog();
		}
	}
	public void computerRSP() {
		switch (computerChoose) {
			case 1 :
				computerRSPLog = "\nComputer가 가위를 냈습니다.";
				break;
			case 2 :
				computerRSPLog = "\nComputer가 바위를 냈습니다.";
				break;
			case 3 :
				computerRSPLog = "\nComputer 보를 냈습니다.";
				break;
		}
	}
	
	public void playerRSP() {
		switch (playerChoose) {
			case 1 :
				playerRSPLog = "\nPlayer가 가위를 냈습니다.";
				break;
			case 2 :
				playerRSPLog = "\nPlayer가 바위를 냈습니다.";
				break;
			case 3 :
				playerRSPLog = "\nPlayer가 보를 냈습니다.";
				break;
		}
	}
	public void winningBonus() {
		if(winCount >= 3) {
			bonusCheck = 1;
			winBonusLog = "\n3연승 보너스를 받습니다";
			playerCoinCount += 10;
			
		}
	}
	public String getBetLog() {
		return betResult;
	}
	public int getError() {
		return errorCheck;
	}
	public String getWinLog() {
		return winLog;
	}
	public String getComputerRSPLog() {
		return computerRSPLog;
	}
	public String getPlayerRSPLog() {
		return playerRSPLog;
	}
	public void resetGame() {
		playerCoinCount = startCoin;
	}
	public void pageCount(int pageCount) {
		if(pageCount == 1) {
			resetGame();
		}
	}
	public String getBonusLog() {
		return winBonusLog;
	}
	public int getBonusCheck () {
		return bonusCheck;
	}
}
