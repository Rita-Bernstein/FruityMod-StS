package fruitymod.seeker.actions.common;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class SelectCardsToShuffleToDrawPileAction
extends AbstractGameAction {
    public static final String[] TEXT = { "shuffle into your draw pile" };
    private AbstractPlayer p;
    private boolean isRandom;
    private boolean isOptional;
    public static int numPlaced;

    public SelectCardsToShuffleToDrawPileAction(AbstractCreature target, AbstractCreature source, int amount, boolean isRandom, boolean isOptional) {
        this.target = target;
        this.p = (AbstractPlayer)target;
        this.setValues(target, source, amount);
        this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
        this.isOptional = isOptional;
        this.isRandom = isRandom;
    }
    
    public SelectCardsToShuffleToDrawPileAction(AbstractCreature target, AbstractCreature source, int amount) {
    	this(target, source, amount, false, false);
    }

    @Override
    public void update() {
        if (this.duration == 0.5f) {
            int i;
            if (this.p.hand.size() < this.amount) {
                this.amount = this.p.hand.size();
            }
            if (this.isRandom) {
                for (i = 0; i < this.amount; ++i) {
                    this.p.hand.moveToDeck(this.p.hand.getRandomCard(false), true);
                }
            } else {
                if (this.p.hand.group.size() > this.amount) {
                    numPlaced = this.amount;
                    AbstractDungeon.handCardSelectScreen.open(TEXT[0], this.amount, this.isOptional);
                    this.tickDuration();
                    return;
                }
                for (i = 0; i < this.p.hand.size(); ++i) {
                    this.p.hand.moveToDeck(this.p.hand.getRandomCard(false), true);
                }
            }
        }
        if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
            for (AbstractCard c : AbstractDungeon.handCardSelectScreen.selectedCards.group) {
                this.p.hand.moveToDeck(c, true);
            }
            AbstractDungeon.player.hand.refreshHandLayout();
            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
        }
        this.tickDuration();
    }
}

