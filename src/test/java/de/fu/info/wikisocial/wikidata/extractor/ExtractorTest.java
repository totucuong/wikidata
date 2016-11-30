package de.fu.info.wikisocial.wikidata.extractor;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by totucuong-standard on 9/22/16.
 */
public class ExtractorTest {
    @Test
    public void extract_user() throws Exception {
        String msg = "Import blank location map as item image ([1]) really does not make any sense." +
                " Also adding \"imported from xxwiki\" as reference makes only a little sense (in this" +
                " case just consider, if it is really needed). --Jklamo (talk) 07:31, 30 April 2014 (UTC)";
        assertEquals("Jklamo", Extractor.extract_user(msg));

    }

    @Test
    public void extract_user2() throws Exception {
        String msg = "Q4918 was created as a duplicate of it, but with a lot more entries. " +
                "As there is no merge currently, I deleted the one with less links, and moved them over. " +
                "See conversation above Reedy (talk) 16:05, 1 November 2012 (UTC)";
        assertEquals("Reedy", Extractor.extract_user(msg));

    }

    @Test
    public void extract_user3() throws Exception {
        String msg = "BTW: you could also use Special:AllMessages?prefix=MainPage&filter=all&lang=en-gb" +
                " to get the content of the messages. Helder 22:52, 3 November 2012 (UTC)";
        assertEquals("Helder", Extractor.extract_user(msg));
    }


    @Test
    public void extract_user_from_html() throws  Exception {
        String html = "<p>specially when removing sitelinks. <a class=\"external text\" href=\"http://www.wikidata.org" +
                "/w/index.php?title=User_talk%3AAndreasmperu&amp;diff=48620792&amp;oldid=48352515\">Further explanation " +
                "here</a>. <a href=\"/wiki/User:Andreasmperu\" title=\"User:Andreasmperu\"><font color=\"black\">" +
                "Andreasm</font></a> <sup><a href=\"/wiki/User_talk:Andreasmperu\" title=\"User talk:Andreasmperu\"" +
                "><font color=\"green\">háblame / just talk to me</font></a></sup> 04:37, 31 May 2013 (UTC)</p>";
        Document doc = Jsoup.parseBodyFragment(html);
        Element msg = doc.body();
        assertEquals("Andreasm", Extractor.extract_user(msg));
    }

    @Test
    public void extract_user_from_html2() throws Exception {
        String html = "<p>I presume <a href=\"https://cs.wikipedia.org/wiki/Muflon\" class=\"extiw\" title=\"cs:Muflon\">" +
                "cs:Muflon</a> should be linked to <a href=\"/wiki/Q993274\" title=\"Q993274\">Q993274</a> because this" +
                " article describes european muflon (Ovis musimon = Ovis orientalis musimon). " +
                "The article about asiatic mouflon or mouflon in wide sense (Ovis orientalis = Ovis gmelini) " +
                "currently does not exist in cs-wiki. -- <a href=\"/w/index.php?title=User:Alexander_Shatulin&amp;" +
                "action=edit&amp;redlink=1\" class=\"new\" title=\"User:Alexander Shatulin (page does not exist)\">" +
                "Alexander Shatulin</a> (<a href=\"/wiki/User_talk:Alexander_Shatulin\" title=\"User talk:Alexander " +
                "Shatulin\">talk</a>) 10:47, 13 March 2013 (UTC)</p>";
        Document doc = Jsoup.parseBodyFragment(html);
        Element msg = doc.body();
        assertEquals("Alexander_Shatulin", Extractor.extract_user(msg));
    }

    @Test
    public void extract_users() throws Exception {
        String content = "Some geographical items are listing more historical periods than necessary and it's a bit arbitrary to decide which historical governments should count as countries and which shouldn't. For example, there are German towns that have P17= German Empire, Weimar Republic, Nazi Germany, Allied-occupied Germany, East Germany, and Germany. I don't think towns need to list every type of government they have been uneder. I propose that we make this property specifically about what modern country the item is in. When we want to list historical countries associated with an item, there are other ways of doing it, like applies to territorial jurisdiction (P1001) for government bodies and country of citizenship (P27) for people. --Arctic.gnome (talk) 19:08, 11 January 2014 (UTC)\n" +
                "\n" +
                "I understand the problem, but it will look strange to have \"Ancient city of Karthago country (P17) Tunisia\". -- Lavallen (talk) 19:16, 11 January 2014 (UTC)\n" +
                "Wikidata should not cover the present only, but also the past. We do not remove information about the profession of individuals just because that individual has retired or died and does not work in his profession any more. Likewise, we should not remove (or forbid) information about what countries an item was related to in history. When we want to find out the current country, we can use date properties on the country and/or date qualifiers on the country (P17) statement. --UV (talk) 20:30, 11 January 2014 (UTC)\n" +
                "Agree. Al lot of information in infoboxes depends not on present, but on past, including, for example, place of birth and death. It should be possible to retrive correct person->place of birth->country information depending on person date of birth, that is used a lot in ruwiki infoboxes. -- Vlsergey (talk) 04:11, 13 February 2014 (UTC)";
        assertEquals(4, Extractor.extract_users(content).size());
    }

    @Test
    public void count_number_item_mentions() throws Exception {
        String content = "Ahoj. Jsem rád, že jsem na wikidatech nalezl česky mluvícího správce. Našel jsem dvojici položek, které by měly být sloučeny do jedné. Jedná se o Q9537742 a q985073. Mohl bys to prosím zařídit? Děkuju. --Unpocoloco (talk) 20:08, 6 August 2013 (UTC)\n" +
                "\n" +
                "Bohužel to nejde, protože položka Q985073 sestává z článků a Q9537742 je kategorie. Matěj Suchánek (talk) 06:01, 7 August 2013 (UTC)\n" +
                "Ještě jednou já. V předchozích příspěvkách jsem se překlepl - obě zmíněné položky mají být kategorie: Q8869520 s cizojazyčnými a Q9537742 je kód jediné české kategorie, která by se měla zařadit do té vícejazyčné. --Unpocoloco (talk) 12:35, 8 August 2013 (UTC)\n" +
                "→ ← Merged. Klidně si zas řekni... (Aunque de momento estoy bastante preocupado, señor Loco.) Littledogboy (talk) 12:58, 8 August 2013 (UTC)\n" +
                "Doplnil jsem polskej a portugalskej odkaz, ale zdá se mi, že ten portugalskej plete dohromady protektoráty a poručenská území...? Littledogboy (talk) 19:57, 14 August 2013 (UTC)\n" +
                "S portugalským názvem je to (pravděpodobně) v pořádku. I hlavní portugalský článek o Poručenských území má v sobě slovo \"protectorato\". --Unpocoloco (talk) 07:48, 15 August 2013 (UTC)";
        assertEquals(6, Extractor.count_num_item_mentions(content));
    }

    @Test
    public void extract_items() throws Exception {
        String content = "Ahoj. Jsem rád, že jsem na wikidatech nalezl česky mluvícího správce. Našel jsem dvojici položek, které by měly být sloučeny do jedné. Jedná se o Q9537742 a q985073. Mohl bys to prosím zařídit? Děkuju. --Unpocoloco (talk) 20:08, 6 August 2013 (UTC)\n";
        assertEquals(2, Extractor.extract_artifacts(content).size());

    }

    @Test
    public void count_number_prop_mentions() throws Exception {
        String content = "By the way, I reverted your edits in Bliss (Q2368) because it should rather be coordinates of the point of view (P1259) than coordinate location (P625) (the difference may seem like hair-splitting in this case, but having two different properties is necesssary for non-numeric photos). --Zolo (talk) 17:17, 11 May 2014 (UTC)";
        assertEquals(2, Extractor.count_num_prop_mentions(content));

    }

    @Test
    public void count_number_of_msgs() throws Exception {
        String content = "By the way, I reverted your edits in Bliss (Q2368) because it should rather be coordinates of the point of view (P1259) than coordinate location (P625) (the difference may seem like hair-splitting in this case, but having two different properties is necesssary for non-numeric photos). --Zolo (talk) 17:17, 11 May 2014 (UTC)\n" +
                "\n" +
                "Oh I see that you had spotted the issue with precision, but it shows up in many items. Can you make sure the values you enter are correct before resuming the bot ? Thanks. --Zolo (talk) 17:21, 11 May 2014 (UTC)\n" +
                "The precision is correct, it's just the Wikibase software that contains a bug, see bugzilla:62105. Don't block if you don't understand what you're doing. Resumed the bot. Multichill (talk) 17:56, 11 May 2014 (UTC)\n" +
                "If display is buggish, maybe it's best to wait until it works. But does a precision of \"0.0147\" really makes sense ? What does it correspond to ? --Zolo (talk) 21:38, 12 May 2014 (UTC)\n" +
                "Wikipedia uses \"dim\" for the dimension in meters (more info). Precision is in degrees. The dim in meters gets converted to degrees (code). Multichill (talk) 18:03, 16 May 2014 (UTC)";
        assertEquals(5, Extractor.count_num_msgs(content));
    }
}