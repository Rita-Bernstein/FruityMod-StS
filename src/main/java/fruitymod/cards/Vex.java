package fruitymod.cards;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.VerticalAuraEffect;

import basemod.abstracts.CustomCard;
import fruitymod.patches.AbstractCardEnum;

public class Vex extends CustomCard {
	 public static final String ID = "Vex";
	    public static final String NAME = "Retrograde";
	    public static final String DESCRIPTION = "Deal !D! damage. Shuffle a copy of this card into your Draw pile.";
	    private static final int COST = 0;
	    private static final int ATTACK_DMG = 4;
	    private static final int POOL = 1;
	    
	 public Vex() {
		 super(ID, NAME, "images/cards/locked_attack.png", COST, DESCRIPTION, AbstractCard.CardType.ATTACK, AbstractCardEnum.PURPLE, AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.ENEMY, POOL);
	        this.damage = this.baseDamage = ATTACK_DMG;
	    }
	 
	 @Override
	    public void use(AbstractPlayer p, AbstractMonster m) {
	        AbstractDungeon.actionManager.addToBottom(new DamageAction((AbstractCreature)m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
	        AbstractDungeon.actionManager.addToBottom(new VFXAction(p, new VerticalAuraEffect(Color.FIREBRICK, p.hb.cX, p.hb.cY), 0.0f));
	        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDiscardAction(this.makeStatEquivalentCopy(), 1));
	    }

	    @Override
	    public AbstractCard makeCopy() {
	        return new Vex();
	    }

	    @Override
	    public void upgrade() {
	        if (!this.upgraded) {
	            this.upgradeName();
	            this.upgradeDamage(2);
	        }
	    }
}
