package de.fu.info.wikisocial.executables;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 * Created by totucuong-standard on 9/22/16.
 */
public class JSoupExperiment {

    public static void main(String[] args) {
        Document document = Jsoup.parse("<h2><span class=\"mw-headline\" id=\"Importing_blank_location_maps\">Importing blank location maps</span></h2>\n" +
                "<p>Import blank location map as item image (<a class=\"external autonumber\" href=\"//www.wikidata.org/w/index.php?title=Q2788522&amp;diff=123860464&amp;oldid=99110511\">[1]</a>) really does not make any sense. Also adding \"imported from xxwiki\" as reference makes only a little sense (in this case just consider, if it is really needed). --<a href=\"/wiki/User:Jklamo\" title=\"User:Jklamo\">Jklamo</a> (<a href=\"/wiki/User_talk:Jklamo\" title=\"User talk:Jklamo\">talk</a>) 07:31, 30 April 2014 (UTC)</p>\n" +
                "<dl>\n" +
                "<dd>Jklamo, I use <a href=\"//www.mediawiki.org/wiki/Manual:Pywikibot/illustrate_wikidata.py\" class=\"extiw\" title=\"mw:Manual:Pywikibot/illustrate wikidata.py\">illustrate wikidata.py</a>. It uses the image from <a href=\"//www.mediawiki.org/wiki/Extension:PageImages\" class=\"extiw\" title=\"mw:Extension:PageImages\">Extension:PageImages</a>. I think this extension doesn't consider the logo at <a href=\"https://nl.wikipedia.org/wiki/Detroit/Hamtramck_Assembly\" class=\"extiw\" title=\"nl:Detroit/Hamtramck Assembly\">nl:Detroit/Hamtramck Assembly</a> and falls back to the second image in the page. <a class=\"external text\" href=\"//www.wikidata.org/w/index.php?title=Q2788522&amp;diff=124053574&amp;oldid=124026649\">This</a> should prevent any future edits. It was decided a while back that bot should add the source from where the information is imported should be added (sorry no link). My bots comply to that. <a href=\"/wiki/User:Multichill\" title=\"User:Multichill\">Multichill</a> (<a href=\"/wiki/User_talk:Multichill\" title=\"User talk:Multichill\">talk</a>) 18:57, 30 April 2014 (UTC)\n" +
                "<dl>\n" +
                "<dd>\n" +
                "<dl>\n" +
                "<dd>I am not sure if that script is suitable for bot use without human control. I am importing images using \"Wikidata Usefuls\" script and cases, when images from articles are not suitable to use for P18 (but may be suitable for another \"image property\"), are not rare. At least some expection list will be usefull. --<a href=\"/wiki/User:Jklamo\" title=\"User:Jklamo\">Jklamo</a> (<a href=\"/wiki/User_talk:Jklamo\" title=\"User talk:Jklamo\">talk</a>) 09:50, 2 May 2014 (UTC)\n" +
                "<dl>\n" +
                "<dd>I wouldn't run it on all articles. I only run it on articles which have an infobox with an image set. That's a much smaller subset with a much higher success rate. The only thing I see every once in a while is that the image is too small so the extension skips it to the next image (the blank map in this case). <a href=\"/wiki/User:Multichill\" title=\"User:Multichill\">Multichill</a> (<a href=\"/wiki/User_talk:Multichill\" title=\"User talk:Multichill\">talk</a>) 17:47, 2 May 2014 (UTC)</dd>\n" +
                "</dl>\n" +
                "</dd>\n" +
                "</dl>\n" +
                "</dd>\n" +
                "</dl>\n" +
                "</dd>\n" +
                "</dl>","");
        System.out.println(document.toString());
        System.out.println("========================================================================================");
        System.out.println(document.text());
    }
}
