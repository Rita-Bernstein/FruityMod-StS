package fruitymod.cards;

import fruitymod.actions.unique.IlluminateAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basemod.abstracts.CustomCard;
import fruitymod.FruityMod;
import fruitymod.patches.AbstractCardEnum;

public class Illuminate extends CustomCard {
	public static final String ID = "Illuminate";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 1;
	private static final int DUPLICATE_AMT = 1;
	private static final int DUPLICATE_UPGRADE = 1;
	private static final int POOL = 1;

	public Illuminate() {
		super(ID, NAME, FruityMod.makePath(FruityMod.ILLUMINATE), COST, DESCRIPTION, AbstractCard.CardType.SKILL,
				AbstractCardEnum.PURPLE, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF, POOL);

		this.magicNumber = this.baseMagicNumber = DUPLICATE_AMT;
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new IlluminateAction(p, this.magicNumber));
	}

	@Override
	public AbstractCard makeCopy() {
		return new Illuminate();
	}

	@Override
	public void upgrade() {
		if (!this.upgraded) {
			this.upgradeName();
			this.upgradeMagicNumber(DUPLICATE_UPGRADE);
		}
	}
}
