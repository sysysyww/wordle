/**
 * Title      : mainInterface.java
 * Description: This class contains the definition of mainInterface.
 *
 * @author Yi Shi
 * @version 1.0
 */

package Wordle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class mainInterface extends JFrame {

    /**
     * An inner class, defines the key action.
     */
    private class myKeyListener extends KeyAdapter {

        /**
         * This method listens the key released event.
         *
         * @param e The key event.
         */
        @Override
        public void keyReleased(KeyEvent e) {
            int keyCode = e.getKeyCode();
            char c = e.getKeyChar();

            //if user has typed a letter
            if (Character.isLetter(c)) {
                //user have entered every letters
                if (numberEntered == 30) return;
                var currentLabel = gamePanel.letters[numberEntered];

                //this means user has entered 5 letters, and they attempt to enter another
                if (state && numberEntered % 5 == 0) return;
                currentLabel.setText("" + Character.toUpperCase(c));
                numberEntered++;
                if (numberEntered % 5 == 0) state = true;//set the button's state to "ok to press"

            }

            //if user pressed the ENTER
            else if (keyCode == '\n') {
                enter();
            }

            //if user pressed BACKSPACE
            else if (keyCode == 8) {
                if (numberEntered % 5 == 0 && !state) return;
                else {
                    var currentLabel = gamePanel.letters[--numberEntered];
                    currentLabel.setText("");
                    state = false;
                }
            }

            //if user pressed other unrelated key
            else return;
        }
    }

    /**
     * Stores the letter number user has entered.
     */
    private int numberEntered = 0;

    /**
     * Represents the state of check button.
     */
    private boolean state = false;

    /**
     * The word chosen.
     */
    private String word;

    /**
     * Times user clicked the cheat button.
     */
    private int cheatDegree = 0;

    private JLabel cheatBoard;

    /**
     * The word list.
     */
    private final String[] wordsList = {"CIGAR", "REBUT", "SISSY", "HUMPH", "AWAKE", "BLUSH", "FOCAL", "EVADE", "NAVAL", "SERVE", "HEATH", "DWARF", "MODEL", "KARMA", "STINK", "GRADE", "QUIET", "BENCH", "ABATE", "FEIGN", "MAJOR", "DEATH", "FRESH", "CRUST", "STOOL", "COLON", "ABASE", "MARRY", "REACT", "BATTY", "PRIDE", "FLOSS", "HELIX", "CROAK", "STAFF", "PAPER", "UNFED", "WHELP", "TRAWL", "OUTDO", "ADOBE", "CRAZY", "SOWER", "REPAY", "DIGIT", "CRATE", "CLUCK", "SPIKE", "MIMIC", "POUND", "MAXIM", "LINEN", "UNMET", "FLESH", "BOOBY", "FORTH", "FIRST", "STAND", "BELLY", "IVORY", "SEEDY", "PRINT", "YEARN", "DRAIN", "BRIBE", "STOUT", "PANEL", "CRASS", "FLUME", "OFFAL", "AGREE", "ERROR", "SWIRL", "ARGUE", "BLEED", "DELTA", "FLICK", "TOTEM", "WOOER", "FRONT", "SHRUB", "PARRY", "BIOME", "LAPEL", "START", "GREET", "GONER", "GOLEM", "LUSTY", "LOOPY", "ROUND", "AUDIT", "LYING", "GAMMA", "LABOR", "ISLET", "CIVIC", "FORGE", "CORNY", "MOULT", "BASIC", "SALAD", "AGATE", "SPICY", "SPRAY", "ESSAY", "FJORD", "SPEND", "KEBAB", "GUILD", "ABACK", "MOTOR", "ALONE", "HATCH", "HYPER", "THUMB", "DOWRY", "OUGHT", "BELCH", "DUTCH", "PILOT", "TWEED", "COMET", "JAUNT", "ENEMA", "STEED", "ABYSS", "GROWL", "FLING", "DOZEN", "BOOZY", "ERODE", "WORLD", "GOUGE", "CLICK", "BRIAR", "GREAT", "ALTAR", "PULPY", "BLURT", "COAST", "DUCHY", "GROIN", "FIXER", "GROUP", "ROGUE", "BADLY", "SMART", "PITHY", "GAUDY", "CHILL", "HERON", "VODKA", "FINER", "SURER", "RADIO", "ROUGE", "PERCH", "RETCH", "WROTE", "CLOCK", "TILDE", "STORE", "PROVE", "BRING", "SOLVE", "CHEAT", "GRIME", "EXULT", "USHER", "EPOCH", "TRIAD", "BREAK", "RHINO", "VIRAL", "CONIC", "MASSE", "SONIC", "VITAL", "TRACE", "USING", "PEACH", "CHAMP", "BATON", "BRAKE", "PLUCK", "CRAZE", "GRIPE", "WEARY", "PICKY", "ACUTE", "FERRY", "ASIDE", "TAPIR", "TROLL", "UNIFY", "REBUS", "BOOST", "TRUSS", "SIEGE", "TIGER", "BANAL", "SLUMP", "CRANK", "GORGE", "QUERY", "DRINK", "FAVOR", "ABBEY", "TANGY", "PANIC", "SOLAR", "SHIRE", "PROXY", "POINT", "ROBOT", "PRICK", "WINCE", "CRIMP", "KNOLL", "SUGAR", "WHACK", "MOUNT", "PERKY", "COULD", "WRUNG", "LIGHT", "THOSE", "MOIST", "SHARD", "PLEAT", "ALOFT", "SKILL", "ELDER", "FRAME", "HUMOR", "PAUSE", "ULCER", "ULTRA", "ROBIN", "CYNIC", "AROMA", "CAULK", "SHAKE", "DODGE", "SWILL", "TACIT", "OTHER", "THORN", "TROVE", "BLOKE", "VIVID", "SPILL", "CHANT", "CHOKE", "RUPEE", "NASTY", "MOURN", "AHEAD", "BRINE", "CLOTH", "HOARD", "SWEET", "MONTH", "LAPSE", "WATCH", "TODAY", "FOCUS", "SMELT", "TEASE", "CATER", "MOVIE", "SAUTE", "ALLOW", "RENEW", "THEIR", "SLOSH", "PURGE", "CHEST", "DEPOT", "EPOXY", "NYMPH", "FOUND", "SHALL", "HARRY", "STOVE", "LOWLY", "SNOUT", "TROPE", "FEWER", "SHAWL", "NATAL", "COMMA", "FORAY", "SCARE", "STAIR", "BLACK", "SQUAD", "ROYAL", "CHUNK", "MINCE", "SHAME", "CHEEK", "AMPLE", "FLAIR", "FOYER", "CARGO", "OXIDE", "PLANT", "OLIVE", "INERT", "ASKEW", "HEIST", "SHOWN", "ZESTY", "HASTY", "TRASH", "FELLA", "LARVA", "FORGO", "STORY", "HAIRY", "TRAIN", "HOMER", "BADGE", "MIDST", "CANNY", "FETUS", "BUTCH", "FARCE", "SLUNG", "TIPSY", "METAL", "YIELD", "DELVE", "BEING", "SCOUR", "GLASS", "GAMER", "SCRAP", "MONEY", "HINGE", "ALBUM", "VOUCH", "ASSET", "TIARA", "CREPT", "BAYOU", "ATOLL", "MANOR", "CREAK", "SHOWY", "PHASE", "FROTH", "DEPTH", "GLOOM", "FLOOD", "TRAIT", "GIRTH", "PIETY", "PAYER", "GOOSE", "FLOAT", "DONOR", "ATONE", "PRIMO", "APRON", "BLOWN", "CACAO", "LOSER", "INPUT", "GLOAT", "AWFUL", "BRINK", "SMITE", "BEADY", "RUSTY", "RETRO", "DROLL", "GAWKY", "HUTCH", "PINTO", "GAILY", "EGRET", "LILAC", "SEVER", "FIELD", "FLUFF", "HYDRO", "FLACK", "AGAPE", "VOICE", "STEAD", "STALK", "BERTH", "MADAM", "NIGHT", "BLAND", "LIVER", "WEDGE", "AUGUR", "ROOMY", "WACKY", "FLOCK", "ANGRY", "BOBBY", "TRITE", "APHID", "TRYST", "MIDGE", "POWER", "ELOPE", "CINCH", "MOTTO", "STOMP", "UPSET", "BLUFF", "CRAMP", "QUART", "COYLY", "YOUTH", "RHYME", "BUGGY", "ALIEN", "SMEAR", "UNFIT", "PATTY", "CLING", "GLEAN", "LABEL", "HUNKY", "KHAKI", "POKER", "GRUEL", "TWICE", "TWANG", "SHRUG", "TREAT", "UNLIT", "WASTE", "MERIT", "WOVEN", "OCTAL", "NEEDY", "CLOWN", "WIDOW", "IRONY", "RUDER", "GAUZE", "CHIEF", "ONSET", "PRIZE", "FUNGI", "CHARM", "GULLY", "INTER", "WHOOP", "TAUNT", "LEERY", "CLASS", "THEME", "LOFTY", "TIBIA", "BOOZE", "ALPHA", "THYME", "ECLAT", "DOUBT", "PARER", "CHUTE", "STICK", "TRICE", "ALIKE", "SOOTH", "RECAP", "SAINT", "LIEGE", "GLORY", "GRATE", "ADMIT", "BRISK", "SOGGY", "USURP", "SCALD", "SCORN", "LEAVE", "TWINE", "STING", "BOUGH", "MARSH", "SLOTH", "DANDY", "VIGOR", "HOWDY", "ENJOY", "VALID", "IONIC", "EQUAL", "UNSET", "FLOOR", "CATCH", "SPADE", "STEIN", "EXIST", "QUIRK", "DENIM", "GROVE", "SPIEL", "MUMMY", "FAULT", "FOGGY", "FLOUT", "CARRY", "SNEAK", "LIBEL", "WALTZ", "APTLY", "PINEY", "INEPT", "ALOUD", "PHOTO", "DREAM", "STALE", "VOMIT", "OMBRE", "FANNY", "UNITE", "SNARL", "BAKER", "THERE", "GLYPH", "POOCH", "HIPPY", "SPELL", "FOLLY", "LOUSE", "GULCH", "VAULT", "GODLY", "THREW", "FLEET", "GRAVE", "INANE", "SHOCK", "CRAVE", "SPITE", "VALVE", "SKIMP", "CLAIM", "RAINY", "MUSTY", "PIQUE", "DADDY", "QUASI", "ARISE", "AGING", "VALET", "OPIUM", "AVERT", "STUCK", "RECUT", "MULCH", "GENRE", "PLUME", "RIFLE", "COUNT", "INCUR", "TOTAL", "WREST", "MOCHA", "DETER", "STUDY", "LOVER", "SAFER", "RIVET", "FUNNY", "SMOKE", "MOUND", "UNDUE", "SEDAN", "PAGAN", "SWINE", "GUILE", "GUSTY", "EQUIP", "TOUGH", "CANOE", "CHAOS", "COVET", "HUMAN", "UDDER", "LUNCH", "BLAST", "STRAY", "MANGA", "MELEE", "LEFTY", "QUICK", "PASTE", "GIVEN", "OCTET", "RISEN", "GROAN", "LEAKY", "GRIND", "CARVE", "LOOSE", "SADLY", "SPILT", "APPLE", "SLACK", "HONEY", "FINAL", "SHEEN", "EERIE", "MINTY", "SLICK", "DERBY", "WHARF", "SPELT", "COACH", "ERUPT", "SINGE", "PRICE", "SPAWN", "FAIRY", "JIFFY", "FILMY", "STACK", "CHOSE", "SLEEP", "ARDOR", "NANNY", "NIECE", "WOOZY", "HANDY", "GRACE", "DITTO", "STANK", "CREAM", "USUAL", "DIODE", "VALOR", "ANGLE", "NINJA", "MUDDY", "CHASE", "REPLY", "PRONE", "SPOIL", "HEART", "SHADE", "DINER", "ARSON", "ONION", "SLEET", "DOWEL", "COUCH", "PALSY", "BOWEL", "SMILE", "EVOKE", "CREEK", "LANCE", "EAGLE", "IDIOT", "SIREN", "BUILT", "EMBED", "AWARD", "DROSS", "ANNUL", "GOODY", "FROWN", "PATIO", "LADEN", "HUMID", "ELITE", "LYMPH", "EDIFY", "MIGHT", "RESET", "VISIT", "GUSTO", "PURSE", "VAPOR", "CROCK", "WRITE", "SUNNY", "LOATH", "CHAFF", "SLIDE", "QUEER", "VENOM", "STAMP", "SORRY", "STILL", "ACORN", "APING", "PUSHY", "TAMER", "HATER", "MANIA", "AWOKE", "BRAWN", "SWIFT", "EXILE", "BIRCH", "LUCKY", "FREER", "RISKY", "GHOST", "PLIER", "LUNAR", "WINCH", "SNARE", "NURSE", "HOUSE", "BORAX", "NICER", "LURCH", "EXALT", "ABOUT", "SAVVY", "TOXIN", "TUNIC", "PRIED", "INLAY", "CHUMP", "LANKY", "CRESS", "EATER", "ELUDE", "CYCLE", "KITTY", "BOULE", "MORON", "TENET", "PLACE", "LOBBY", "PLUSH", "VIGIL", "INDEX", "BLINK", "CLUNG", "QUALM", "CROUP", "CLINK", "JUICY", "STAGE", "DECAY", "NERVE", "FLIER", "SHAFT", "CROOK", "CLEAN", "CHINA", "RIDGE", "VOWEL", "GNOME", "SNUCK", "ICING", "SPINY", "RIGOR", "SNAIL", "FLOWN", "RABID", "PROSE", "THANK", "POPPY", "BUDGE", "FIBER", "MOLDY", "DOWDY", "KNEEL", "TRACK", "CADDY", "QUELL", "DUMPY", "PALER", "SWORE", "REBAR", "SCUBA", "SPLAT", "FLYER", "HORNY", "MASON", "DOING", "OZONE", "AMPLY", "MOLAR", "OVARY", "BESET", "QUEUE", "CLIFF", "MAGIC", "TRUCE", "SPORT", "FRITZ", "EDICT", "TWIRL", "VERSE", "LLAMA", "EATEN", "RANGE", "WHISK", "HOVEL", "REHAB", "MACAW", "SIGMA", "SPOUT", "VERVE", "SUSHI", "DYING", "FETID", "BRAIN", "BUDDY", "THUMP", "SCION", "CANDY", "CHORD", "BASIN", "MARCH", "CROWD", "ARBOR", "GAYLY", "MUSKY", "STAIN", "DALLY", "BLESS", "BRAVO", "STUNG", "TITLE", "RULER", "KIOSK", "BLOND", "ENNUI", "LAYER", "FLUID", "TATTY", "SCORE", "CUTIE", "ZEBRA", "BARGE", "MATEY", "BLUER", "AIDER", "SHOOK", "RIVER", "PRIVY", "BETEL", "FRISK", "BONGO", "BEGUN", "AZURE", "WEAVE", "GENIE", "SOUND", "GLOVE", "BRAID", "SCOPE", "WRYLY", "ROVER", "ASSAY", "OCEAN", "BLOOM", "IRATE", "LATER", "WOKEN", "SILKY", "WRECK", "DWELT", "SLATE", "SMACK", "SOLID", "AMAZE", "HAZEL", "WRIST", "JOLLY", "GLOBE", "FLINT", "ROUSE", "CIVIL", "VISTA", "RELAX", "COVER", "ALIVE", "BEECH", "JETTY", "BLISS", "VOCAL", "OFTEN", "DOLLY", "EIGHT", "JOKER", "SINCE", "EVENT", "ENSUE", "SHUNT", "DIVER", "POSER", "WORST", "SWEEP", "ALLEY", "CREED", "ANIME", "LEAFY", "BOSOM", "DUNCE", "STARE", "PUDGY", "WAIVE", "CHOIR", "STOOD", "SPOKE", "OUTGO", "DELAY", "BILGE", "IDEAL", "CLASP", "SEIZE", "HOTLY", "LAUGH", "SIEVE", "BLOCK", "MEANT", "GRAPE", "NOOSE", "HARDY", "SHIED", "DRAWL", "DAISY", "PUTTY", "STRUT", "BURNT", "TULIP", "CRICK", "IDYLL", "VIXEN", "FUROR", "GEEKY", "COUGH", "NAIVE", "SHOAL", "STORK", "BATHE", "AUNTY", "CHECK", "PRIME", "BRASS", "OUTER", "FURRY", "RAZOR", "ELECT", "EVICT", "IMPLY", "DEMUR", "QUOTA", "HAVEN", "CAVIL", "SWEAR", "CRUMP", "DOUGH", "GAVEL", "WAGON", "SALON", "NUDGE", "HAREM", "PITCH", "SWORN", "PUPIL", "EXCEL", "STONY", "CABIN", "UNZIP", "QUEEN", "TROUT", "POLYP", "EARTH", "STORM", "UNTIL", "TAPER", "ENTER", "CHILD", "ADOPT", "MINOR", "FATTY", "HUSKY", "BRAVE", "FILET", "SLIME", "GLINT", "TREAD", "STEAL", "REGAL", "GUEST", "EVERY", "MURKY", "SHARE", "SPORE", "HOIST", "BUXOM", "INNER", "OTTER", "DIMLY", "LEVEL", "SUMAC", "DONUT", "STILT", "ARENA", "SHEET", "SCRUB", "FANCY", "SLIMY", "PEARL", "SILLY", "PORCH", "DINGO", "SEPIA", "AMBLE", "SHADY", "BREAD", "FRIAR", "REIGN", "DAIRY", "QUILL", "CROSS", "BROOD", "TUBER", "SHEAR", "POSIT", "BLANK", "VILLA", "SHANK", "PIGGY", "FREAK", "WHICH", "AMONG", "FECAL", "SHELL", "WOULD", "ALGAE", "LARGE", "RABBI", "AGONY", "AMUSE", "BUSHY", "COPSE", "SWOON", "KNIFE", "POUCH", "ASCOT", "PLANE", "CROWN", "URBAN", "SNIDE", "RELAY", "ABIDE", "VIOLA", "RAJAH", "STRAW", "DILLY", "CRASH", "AMASS", "THIRD", "TRICK", "TUTOR", "WOODY", "BLURB", "GRIEF", "DISCO", "WHERE", "SASSY", "BEACH", "SAUNA", "COMIC", "CLUED", "CREEP", "CASTE", "GRAZE", "SNUFF", "FROCK", "GONAD", "DRUNK", "PRONG", "LURID", "STEEL", "HALVE", "BUYER", "VINYL", "UTILE", "SMELL", "ADAGE", "WORRY", "TASTY", "LOCAL", "TRADE", "FINCH", "ASHEN", "MODAL", "GAUNT", "CLOVE", "ENACT", "ADORN", "ROAST", "SPECK", "SHEIK", "MISSY", "GRUNT", "SNOOP", "PARTY", "TOUCH", "MAFIA", "EMCEE", "ARRAY", "SOUTH", "VAPID", "JELLY", "SKULK", "ANGST", "TUBAL", "LOWER", "CREST", "SWEAT", "CYBER", "ADORE", "TARDY", "SWAMI", "NOTCH", "GROOM", "ROACH", "HITCH", "YOUNG", "ALIGN", "READY", "FROND", "STRAP", "PUREE", "REALM", "VENUE", "SWARM", "OFFER", "SEVEN", "DRYER", "DIARY", "DRYLY", "DRANK", "ACRID", "HEADY", "THETA", "JUNTO", "PIXIE", "QUOTH", "BONUS", "SHALT", "PENNE", "AMEND", "DATUM", "BUILD", "PIANO", "SHELF", "LODGE", "SUING", "REARM", "CORAL", "RAMEN", "WORTH", "PSALM", "INFER", "OVERT", "MAYOR", "OVOID", "GLIDE", "USAGE", "POISE", "RANDY", "CHUCK", "PRANK", "FISHY", "TOOTH", "ETHER", "DROVE", "IDLER", "SWATH", "STINT", "WHILE", "BEGAT", "APPLY", "SLANG", "TAROT", "RADAR", "CREDO", "AWARE", "CANON", "SHIFT", "TIMER", "BYLAW", "SERUM", "THREE", "STEAK", "ILIAC", "SHIRK", "BLUNT", "PUPPY", "PENAL", "JOIST", "BUNNY", "SHAPE", "BEGET", "WHEEL", "ADEPT", "STUNT", "STOLE", "TOPAZ", "CHORE", "FLUKE", "AFOOT", "BLOAT", "BULLY", "DENSE", "CAPER", "SNEER", "BOXER", "JUMBO", "LUNGE", "SPACE", "AVAIL", "SHORT", "SLURP", "LOYAL", "FLIRT", "PIZZA", "CONCH", "TEMPO", "DROOP", "PLATE", "BIBLE", "PLUNK", "AFOUL", "SAVOY", "STEEP", "AGILE", "STAKE", "DWELL", "KNAVE", "BEARD", "AROSE", "MOTIF", "SMASH", "BROIL", "GLARE", "SHOVE", "BAGGY", "MAMMY", "SWAMP", "ALONG", "RUGBY", "WAGER", "QUACK", "SQUAT", "SNAKY", "DEBIT", "MANGE", "SKATE", "NINTH", "JOUST", "TRAMP", "SPURN", "MEDAL", "MICRO", "REBEL", "FLANK", "LEARN", "NADIR", "MAPLE", "COMFY", "REMIT", "GRUFF", "ESTER", "LEAST", "MOGUL", "FETCH", "CAUSE", "OAKEN", "AGLOW", "MEATY", "GAFFE", "SHYLY", "RACER", "PROWL", "THIEF", "STERN", "POESY", "ROCKY", "TWEET", "WAIST", "SPIRE", "GROPE", "HAVOC", "PATSY", "TRULY", "FORTY", "DEITY", "UNCLE", "SWISH", "GIVER", "PREEN", "BEVEL", "LEMUR", "DRAFT", "SLOPE", "ANNOY", "LINGO", "BLEAK", "DITTY", "CURLY", "CEDAR", "DIRGE", "GROWN", "HORDE", "DROOL", "SHUCK", "CRYPT", "CUMIN", "STOCK", "GRAVY", "LOCUS", "WIDER", "BREED", "QUITE", "CHAFE", "CACHE", "BLIMP", "DEIGN", "FIEND", "LOGIC", "CHEAP", "ELIDE", "RIGID", "FALSE", "RENAL", "PENCE", "ROWDY", "SHOOT", "BLAZE", "ENVOY", "POSSE", "BRIEF", "NEVER", "ABORT", "MOUSE", "MUCKY", "SULKY", "FIERY", "MEDIA", "TRUNK", "YEAST", "CLEAR", "SKUNK", "SCALP", "BITTY", "CIDER", "KOALA", "DUVET", "SEGUE", "CREME", "SUPER", "GRILL", "AFTER", "OWNER", "EMBER", "REACH", "NOBLY", "EMPTY", "SPEED", "GIPSY", "RECUR", "SMOCK", "DREAD", "MERGE", "BURST", "KAPPA", "AMITY", "SHAKY", "HOVER", "CAROL", "SNORT", "SYNOD", "FAINT", "HAUNT", "FLOUR", "CHAIR", "DETOX", "SHREW", "TENSE", "PLIED", "QUARK", "BURLY", "NOVEL", "WAXEN", "STOIC", "JERKY", "BLITZ", "BEEFY", "LYRIC", "HUSSY", "TOWEL", "QUILT", "BELOW", "BINGO", "WISPY", "BRASH", "SCONE", "TOAST", "EASEL", "SAUCY", "VALUE", "SPICE", "HONOR", "ROUTE", "SHARP", "BAWDY", "RADII", "SKULL", "PHONY", "ISSUE", "LAGER", "SWELL", "URINE", "GASSY", "TRIAL", "FLORA", "UPPER", "LATCH", "WIGHT", "BRICK", "RETRY", "HOLLY", "DECAL", "GRASS", "SHACK", "DOGMA", "MOVER", "DEFER", "SOBER", "OPTIC", "CRIER", "VYING", "NOMAD", "FLUTE", "HIPPO", "SHARK", "DRIER", "OBESE", "BUGLE", "TAWNY", "CHALK", "FEAST", "RUDDY", "PEDAL", "SCARF", "CRUEL", "BLEAT", "TIDAL", "SLUSH", "SEMEN", "WINDY", "DUSTY", "SALLY", "IGLOO", "NERDY", "JEWEL", "SHONE", "WHALE", "HYMEN", "ABUSE", "FUGUE", "ELBOW", "CRUMB", "PANSY", "WELSH", "SYRUP", "TERSE", "SUAVE", "GAMUT", "SWUNG", "DRAKE", "FREED", "AFIRE", "SHIRT", "GROUT", "ODDLY", "TITHE", "PLAID", "DUMMY", "BROOM", "BLIND", "TORCH", "ENEMY", "AGAIN", "TYING", "PESKY", "ALTER", "GAZER", "NOBLE", "ETHOS", "BRIDE", "EXTOL", "DECOR", "HOBBY", "BEAST", "IDIOM", "UTTER", "THESE", "SIXTH", "ALARM", "ERASE", "ELEGY", "SPUNK", "PIPER", "SCALY", "SCOLD", "HEFTY", "CHICK", "SOOTY", "CANAL", "WHINY", "SLASH", "QUAKE", "JOINT", "SWEPT", "PRUDE", "HEAVY", "WIELD", "FEMME", "LASSO", "MAIZE", "SHALE", "SCREW", "SPREE", "SMOKY", "WHIFF", "SCENT", "GLADE", "SPENT", "PRISM", "STOKE", "RIPER", "ORBIT", "COCOA", "GUILT", "HUMUS", "SHUSH", "TABLE", "SMIRK", "WRONG", "NOISY", "ALERT", "SHINY", "ELATE", "RESIN", "WHOLE", "HUNCH", "PIXEL", "POLAR", "HOTEL", "SWORD", "CLEAT", "MANGO", "RUMBA", "PUFFY", "FILLY", "BILLY", "LEASH", "CLOUT", "DANCE", "OVATE", "FACET", "CHILI", "PAINT", "LINER", "CURIO", "SALTY", "AUDIO", "SNAKE", "FABLE", "CLOAK", "NAVEL", "SPURT", "PESTO", "BALMY", "FLASH", "UNWED", "EARLY", "CHURN", "WEEDY", "STUMP", "LEASE", "WITTY", "WIMPY", "SPOOF", "SANER", "BLEND", "SALSA", "THICK", "WARTY", "MANIC", "BLARE", "SQUIB", "SPOON", "PROBE", "CREPE", "KNACK", "FORCE", "DEBUT", "ORDER", "HASTE", "TEETH", "AGENT", "WIDEN", "ICILY", "SLICE", "INGOT", "CLASH", "JUROR", "BLOOD", "ABODE", "THROW", "UNITY", "PIVOT", "SLEPT", "TROOP", "SPARE", "SEWER", "PARSE", "MORPH", "CACTI", "TACKY", "SPOOL", "DEMON", "MOODY", "ANNEX", "BEGIN", "FUZZY", "PATCH", "WATER", "LUMPY", "ADMIN", "OMEGA", "LIMIT", "TABBY", "MACHO", "AISLE", "SKIFF", "BASIS", "PLANK", "VERGE", "BOTCH", "CRAWL", "LOUSY", "SLAIN", "CUBIC", "RAISE", "WRACK", "GUIDE", "FOIST", "CAMEO", "UNDER", "ACTOR", "REVUE", "FRAUD", "HARPY", "SCOOP", "CLIMB", "REFER", "OLDEN", "CLERK", "DEBAR", "TALLY", "ETHIC", "CAIRN", "TULLE", "GHOUL", "HILLY", "CRUDE", "APART", "SCALE", "OLDER", "PLAIN", "SPERM", "BRINY", "ABBOT", "RERUN", "QUEST", "CRISP", "BOUND", "BEFIT", "DRAWN", "SUITE", "ITCHY", "CHEER", "BAGEL", "GUESS", "BROAD", "AXIOM", "CHARD", "CAPUT", "LEANT", "HARSH", "CURSE", "PROUD", "SWING", "OPINE", "TASTE", "LUPUS", "GUMBO", "MINER", "GREEN", "CHASM", "LIPID", "TOPIC", "ARMOR", "BRUSH", "CRANE", "MURAL", "ABLED", "HABIT", "BOSSY", "MAKER", "DUSKY", "DIZZY", "LITHE", "BROOK", "JAZZY", "FIFTY", "SENSE", "GIANT", "SURLY", "LEGAL", "FATAL", "FLUNK", "BEGAN", "PRUNE", "SMALL", "SLANT", "SCOFF", "TORUS", "NINNY", "COVEY", "VIPER", "TAKEN", "MORAL", "VOGUE", "OWING", "TOKEN", "ENTRY", "BOOTH", "VOTER", "CHIDE", "ELFIN", "EBONY", "NEIGH", "MINIM", "MELON", "KNEED", "DECOY", "VOILA", "ANKLE", "ARROW", "MUSHY", "TRIBE", "CEASE", "EAGER", "BIRTH", "GRAPH", "ODDER", "TERRA", "WEIRD", "TRIED", "CLACK", "COLOR", "ROUGH", "WEIGH", "UNCUT", "LADLE", "STRIP", "CRAFT", "MINUS", "DICEY", "TITAN", "LUCID", "VICAR", "DRESS", "DITCH", "GYPSY", "PASTA", "TAFFY", "FLAME", "SWOOP", "ALOOF", "SIGHT", "BROKE", "TEARY", "CHART", "SIXTY", "WORDY", "SHEER", "LEPER", "NOSEY", "BULGE", "SAVOR", "CLAMP", "FUNKY", "FOAMY", "TOXIC", "BRAND", "PLUMB", "DINGY", "BUTTE", "DRILL", "TRIPE", "BICEP", "TENOR", "KRILL", "WORSE", "DRAMA", "HYENA", "THINK", "RATIO", "COBRA", "BASIL", "SCRUM", "BUSED", "PHONE", "COURT", "CAMEL", "PROOF", "HEARD", "ANGEL", "PETAL", "POUTY", "THROB", "MAYBE", "FETAL", "SPRIG", "SPINE", "SHOUT", "CADET", "MACRO", "DODGY", "SATYR", "RARER", "BINGE", "TREND", "NUTTY", "LEAPT", "AMISS", "SPLIT", "MYRRH", "WIDTH", "SONAR", "TOWER", "BARON", "FEVER", "WAVER", "SPARK", "BELIE", "SLOOP", "EXPEL", "SMOTE", "BALER", "ABOVE", "NORTH", "WAFER", "SCANT", "FRILL", "AWASH", "SNACK", "SCOWL", "FRAIL", "DRIFT", "LIMBO", "FENCE", "MOTEL", "OUNCE", "WREAK", "REVEL", "TALON", "PRIOR", "KNELT", "CELLO", "FLAKE", "DEBUG", "ANODE", "CRIME", "SALVE", "SCOUT", "IMBUE", "PINKY", "STAVE", "VAGUE", "CHOCK", "FIGHT", "VIDEO", "STONE", "TEACH", "CLEFT", "FROST", "PRAWN", "BOOTY", "TWIST", "APNEA", "STIFF", "PLAZA", "LEDGE", "TWEAK", "BOARD", "GRANT", "MEDIC", "BACON", "CABLE", "BRAWL", "SLUNK", "RASPY", "FORUM", "DRONE", "WOMEN", "MUCUS", "BOAST", "TODDY", "COVEN", "TUMOR", "TRUER", "WRATH", "STALL", "STEAM", "AXIAL", "PURER", "DAILY", "TRAIL", "NICHE", "MEALY", "JUICE", "NYLON", "PLUMP", "MERRY", "FLAIL", "PAPAL", "WHEAT", "BERRY", "COWER", "ERECT", "BRUTE", "LEGGY", "SNIPE", "SINEW", "SKIER", "PENNY", "JUMPY", "RALLY", "UMBRA", "SCARY", "MODEM", "GROSS", "AVIAN", "GREED", "SATIN", "TONIC", "PARKA", "SNIFF", "LIVID", "STARK", "TRUMP", "GIDDY", "REUSE", "TABOO", "AVOID", "QUOTE", "DEVIL", "LIKEN", "GLOSS", "GAYER", "BERET", "NOISE", "GLAND", "DEALT", "SLING", "RUMOR", "OPERA", "THIGH", "TONGA", "FLARE", "WOUND", "WHITE", "BULKY", "ETUDE", "HORSE", "CIRCA", "PADDY", "INBOX", "FIZZY", "GRAIN", "EXERT", "SURGE", "GLEAM", "BELLE", "SALVO", "CRUSH", "FRUIT", "SAPPY", "TAKER", "TRACT", "OVINE", "SPIKY", "FRANK", "REEDY", "FILTH", "SPASM", "HEAVE", "MAMBO", "RIGHT", "CLANK", "TRUST", "LUMEN", "BORNE", "SPOOK", "SAUCE", "AMBER", "LATHE", "CARAT", "CORER", "DIRTY", "SLYLY", "AFFIX", "ALLOY", "TAINT", "SHEEP", "KINKY", "WOOLY", "MAUVE", "FLUNG", "YACHT", "FRIED", "QUAIL", "BRUNT", "GRIMY", "CURVY", "CAGEY", "RINSE", "DEUCE", "STATE", "GRASP", "MILKY", "BISON", "GRAFT", "SANDY", "BASTE", "FLASK", "HEDGE", "GIRLY", "SWASH", "BONEY", "COUPE", "ENDOW", "ABHOR", "WELCH", "BLADE", "TIGHT", "GEESE", "MISER", "MIRTH", "CLOUD", "CABAL", "LEECH", "CLOSE", "TENTH", "PECAN", "DROIT", "GRAIL", "CLONE", "GUISE", "RALPH", "TANGO", "BIDDY", "SMITH", "MOWER", "PAYEE", "SERIF", "DRAPE", "FIFTH", "SPANK", "GLAZE", "ALLOT", "TRUCK", "KAYAK", "VIRUS", "TESTY", "TEPEE", "FULLY", "ZONAL", "METRO", "CURRY", "GRAND", "BANJO", "AXION", "BEZEL", "OCCUR", "CHAIN", "NASAL", "GOOEY", "FILER", "BRACE", "ALLAY", "PUBIC", "RAVEN", "PLEAD", "GNASH", "FLAKY", "MUNCH", "DULLY", "EKING", "THING", "SLINK", "HURRY", "THEFT", "SHORN", "PYGMY", "RANCH", "WRING", "LEMON", "SHORE", "MAMMA", "FROZE", "NEWER", "STYLE", "MOOSE", "ANTIC", "DROWN", "VEGAN", "CHESS", "GUPPY", "UNION", "LEVER", "LORRY", "IMAGE", "CABBY", "DRUID", "EXACT", "TRUTH", "DOPEY", "SPEAR", "CRIED", "CHIME", "CRONY", "STUNK", "TIMID", "BATCH", "GAUGE", "ROTOR", "CRACK", "CURVE", "LATTE", "WITCH", "BUNCH", "REPEL", "ANVIL", "SOAPY", "METER", "BROTH", "MADLY", "DRIED", "SCENE", "KNOWN", "MAGMA", "ROOST", "WOMAN", "THONG", "PUNCH", "PASTY", "DOWNY", "KNEAD", "WHIRL", "RAPID", "CLANG", "ANGER", "DRIVE", "GOOFY", "EMAIL", "MUSIC", "STUFF", "BLEEP", "RIDER", "MECCA", "FOLIO", "SETUP", "VERSO", "QUASH", "FAUNA", "GUMMY", "HAPPY", "NEWLY", "FUSSY", "RELIC", "GUAVA", "RATTY", "FUDGE", "FEMUR", "CHIRP", "FORTE", "ALIBI", "WHINE", "PETTY", "GOLLY", "PLAIT", "FLECK", "FELON", "GOURD", "BROWN", "THRUM", "FICUS", "STASH", "DECRY", "WISER", "JUNTA", "VISOR", "DAUNT", "SCREE", "IMPEL", "AWAIT", "PRESS", "WHOSE", "TURBO", "STOOP", "SPEAK", "MANGY", "EYING", "INLET", "CRONE", "PULSE", "MOSSY", "STAID", "HENCE", "PINCH", "TEDDY", "SULLY", "SNORE", "RIPEN", "SNOWY", "ATTIC", "GOING", "LEACH", "MOUTH", "HOUND", "CLUMP", "TONAL", "BIGOT", "PERIL", "PIECE", "BLAME", "HAUTE", "SPIED", "UNDID", "INTRO", "BASAL", "SHINE", "GECKO", "RODEO", "GUARD", "STEER", "LOAMY", "SCAMP", "SCRAM", "MANLY", "HELLO", "VAUNT", "ORGAN", "FERAL", "KNOCK", "EXTRA", "CONDO", "ADAPT", "WILLY", "POLKA", "RAYON", "SKIRT", "FAITH", "TORSO", "MATCH", "MERCY", "TEPID", "SLEEK", "RISER", "TWIXT", "PEACE", "FLUSH", "CATTY", "LOGIN", "EJECT", "ROGER", "RIVAL", "UNTIE", "REFIT", "AORTA", "ADULT", "JUDGE", "ROWER", "ARTSY", "RURAL", "SHAVE"};

    /**
     * This method choose a word from the
     * word list randomly, and set it to
     * field word.
     */
    private void chooseWord() {
        int length = wordsList.length;
        int number = (int) (Math.random() * (length - 1));
        word = wordsList[number];
    }

    /**
     * This method check the user input and changes the
     * corresponding color.
     *
     * @param input The word user has typed.
     * @return int If user typed the right answer, then returns 1,
     * if user typed an word not in English, then returns -1,
     * if user typed a valid word but not the answer, then returns 0.
     */
    private int check(String input) {

        //if user figured it out
        if (Objects.equals(input, word)) {
            //set background color green
            for (int i = numberEntered - 5; i < numberEntered; i++) {
                gamePanel.letters[i].setBackground(Color.green);
            }
            return 1;
        }

        //if didn't figure out
        else {
            //determine if it is in the word list
            boolean Flag = false;
            for (String s : wordsList) {
                if (Objects.equals(s, input)) {
                    Flag = true;
                    break;
                }
            }
            //not in the word list
            if (!Flag) {
                return -1;
            }
            //in the word list
            else {
                //change corresponding color
                for (int i = 0; i < 5; i++) {
                    char temp = input.charAt(i);

                    //right place
                    if (word.charAt(i) == temp) gamePanel.letters[numberEntered - 5 + i].setBackground(Color.green);

                        //wrong place
                    else if (word.contains("" + temp))
                        gamePanel.letters[numberEntered - 5 + i].setBackground(Color.yellow);

                        //don't contain this letter
                    else gamePanel.letters[numberEntered - 5 + i].setBackground(Color.gray);

                }
                return 0;
            }
        }
    }

    /**
     * This method is invoked when user click the
     * check button or typed the ENTER key, it checks
     * if this type is valid and if it is, invokes the check
     * method.
     */
    private void enter() {
        //and user hasn't typed 5 letters, this is an invalid input
        if (numberEntered % 5 != 0 || numberEntered == 0) {
            JOptionPane.showMessageDialog(null, "Not enough letters.", "Tip", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        //user has typed 5 letters, this is for check
        else {
            //put all letters to a word
            var userTyped = new StringBuilder();
            for (int i = numberEntered - 5; i < numberEntered; i++) {
                var tLetter = gamePanel.letters[i].getText();
                userTyped.append(tLetter);
            }

            //check user typed word with word list
            int result = check(userTyped.toString());

            //win!
            if (result == 1) {
                state = false;

                //let user choose whether to play again
                String[] options = {"EXIT", "NEXT WORDLE"};
                int response = JOptionPane.showOptionDialog(null, "MAGNIFICENT!   You Win!", "Congratulations!!", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

                //exit
                if (response == 0) {
                    System.exit(0);
                }

                //play again
                else if (response == 1) {

                    //clean the gamePanel
                    for (letterField l : gamePanel.letters) {
                        l.setText("");
                        l.setBackground(Color.LIGHT_GRAY);
                    }
                    numberEntered = 0;
                    state = false;

                    //clean cheatPanel
                    cheatDegree = 0;
                    cheatBoard.setText("");

                    //choose a word
                    this.chooseWord();
                }
            }

            //not in word list
            else if (result == -1) {
                JOptionPane.showMessageDialog(null, "Not in word list.", "Tip", JOptionPane.INFORMATION_MESSAGE);
            }

            //wrong answer
            else if (result == 0) {
                state = false;

                //user have run out of chance
                if (numberEntered == 30) {

                    //let user choose whether to play again
                    String[] options = {"EXIT", "NEXT WORDLE"};
                    int response = JOptionPane.showOptionDialog(null, "The answer is " + word, "WHOOPS", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

                    //exit
                    if (response == 0) {
                        System.exit(0);
                    }

                    //play again
                    else if (response == 1) {

                        //clean gamePanel
                        for (letterField l : gamePanel.letters) {
                            l.setText("");
                            l.setBackground(Color.LIGHT_GRAY);
                        }
                        numberEntered = 0;
                        state = false;

                        //clean cheatPanel
                        cheatDegree = 0;
                        cheatBoard.setText("");

                        //choose a word
                        this.chooseWord();
                    }
                }
            }

        }
    }

    /**
     * This is the constructor for mainInterface,
     * it sets the title, layout of the mainInterface.
     *
     * @param title The title of the frame.
     */
    public mainInterface(String title) {
        super(title);

        //set layout
        this.getContentPane().setLayout(new GridLayout(2, 1));

        //create ui
        var border = BorderFactory.createLineBorder(Color.white, 30);
        var font = new Font(Font.SANS_SERIF, Font.BOLD, 50);

        //get upper game panel
        var myGamePanel = new gamePanel();
        myGamePanel.setBorder(border);
        myGamePanel.addKeyListener(new myKeyListener());

        //get lower button panel
        var buttonPanel = new JPanel(new GridLayout(3, 1));
        var checkButton = new JButton("Check");
        var cheatButton = new JButton("A little help");
        cheatBoard = new JLabel("", JLabel.CENTER);
        checkButton.setFont(font);
        cheatButton.setFont(font);
        cheatBoard.setFont(font);
        checkButton.addActionListener(event -> {
            enter();
            myGamePanel.grabFocus();//need to regrab focus everytime user click the button
        });
        cheatButton.addActionListener(event -> {
            if (cheatDegree > 4) cheatDegree = 4;
            var textShow = word.substring(0, cheatDegree + 1);
            cheatBoard.setText(textShow);
            cheatDegree++;
            myGamePanel.grabFocus();//need to regrab focus everytime user click the button
        });
        buttonPanel.add(checkButton);
        buttonPanel.add(cheatButton);
        buttonPanel.add(cheatBoard);

        //add two panel into contentpane
        this.getContentPane().add(myGamePanel);
        this.getContentPane().add(buttonPanel);

        //get focus on game panel
        myGamePanel.setFocusable(true);

        //chose word
        this.chooseWord();

        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
