package fruitymod.cards;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basemod.abstracts.CustomCard;
import fruitymod.FruityMod;
import fruitymod.patches.AbstractCardEnum;

public class Defend_Purple extends CustomCard {
	public static final String ID = "Defend_P";
	public static final String NAME = "Defend";
	public static final String DESCRIPTION = "Gain !B! Block.";
	private static final int COST = 1;
	private static final int BLOCK_AMT = 5;
	private static final int UPGRADE_PLUS_BLOCK = 3;
	private static final int POOL = 0;

	public Defend_Purple() {
		super(ID, NAME, FruityMod.makePath(FruityMod.DEFEND_PURPLE), COST, DESCRIPTION, AbstractCard.CardType.SKILL,
				AbstractCardEnum.PURPLE, AbstractCard.CardRarity.BASIC, AbstractCard.CardTarget.SELF, POOL);

		this.baseBlock = BLOCK_AMT;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		if (com.megacrit.cardcrawl.core.Settings.isDebug) {
			AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, 50));
		} else {
			AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.block));
		}
	}

	public AbstractCard makeCopy() {
		return new Defend_Purple();
	}

	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeBlock(UPGRADE_PLUS_BLOCK);
		}
	}
}