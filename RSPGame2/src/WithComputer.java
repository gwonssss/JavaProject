public class WithComputer {
//��ưŬ�� �� RSP�� bettingDialog���� �Է��ϴ� betCoin �޾ƿ�
//1 : ���� 		2 : ����		 3 : ��
//COMPUTER�� RSP���� Math.random()���� �����ϰ� ����
//COMPUTER�� RSP���� ���缭 �� ���� �������� winLog�� ���
//COMPUTER�� RSP���� computerRSPLog�� �ٲ㼭 winLog�� ���� �� ���
	//����ġ���̽��� ����
//Player 3���� �� 3���� ���ʽ� �αװ� �߸鼭 ����+
	//���� �޼ҵ�
//3������ �ƴ� �ÿ��� �̰��� �� 2��, ����� �� 0, ���� �� ������ ���� �Ҹ�
//������ ���� 100�̵Ǹ� ���� �¸�, Player�� ������ 0�� �Ǹ� ���� �й�(winDialog, LoseDialog)


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
			betResult = "\n������ ���� : " + playerBetCoin + "\n���� ���� : " + playerCoinCount; 
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
		winLog = "\nplayer�� �̰���ϴ�. ���� ���ο� 2�踦 �����ɴϴ�. \n���� ���� : " + playerCoinCount+ "\n";
		if(playerCoinCount >= 100) {
		  RSPFrame.playerWinDialog();
		}
	}
	public void draw() {
		playerCoinCount += playerBetCoin;
		winCount = 0;
		bonusCheck = 0;
		winLog = "\n�����ϴ�. ������ ������ �ǵ��ƿɴϴ�. \n���� ���� : " + playerCoinCount + "\n";
	}
	public void lose() {
		winCount = 0;
		bonusCheck = 0;
		winLog = "\ncomputer�� �̰���ϴ�. ������ �پ��ϴ�. \n���� ���� : " + playerCoinCount+ "\n";
		if(playerCoinCount <= 0) {
		 RSPFrame.playerLoseDialog();
		}
	}
	public void computerRSP() {
		switch (computerChoose) {
			case 1 :
				computerRSPLog = "\nComputer�� ������ �½��ϴ�.";
				break;
			case 2 :
				computerRSPLog = "\nComputer�� ������ �½��ϴ�.";
				break;
			case 3 :
				computerRSPLog = "\nComputer ���� �½��ϴ�.";
				break;
		}
	}
	
	public void playerRSP() {
		switch (playerChoose) {
			case 1 :
				playerRSPLog = "\nPlayer�� ������ �½��ϴ�.";
				break;
			case 2 :
				playerRSPLog = "\nPlayer�� ������ �½��ϴ�.";
				break;
			case 3 :
				playerRSPLog = "\nPlayer�� ���� �½��ϴ�.";
				break;
		}
	}
	public void winningBonus() {
		if(winCount >= 3) {
			bonusCheck = 1;
			winBonusLog = "\n3���� ���ʽ��� �޽��ϴ�";
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
