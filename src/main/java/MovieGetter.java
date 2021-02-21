import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

public class MovieGetter {

    public List<String> getData() throws IOException {
        List<String> refList = new ArrayList<>();
//        String regex1 = "https://zakupki.gov.ru/.*";
//        String regex2 = ".*common-info.html\\?regNumber=.*";
//        String regex3 = "https://zakupki.gov.ru/epz/order/notice//*";
//        for (int i = 1; i <= 1; i++) {
//            String myStr = "https://zakupki.gov.ru/epz/order/extendedsearch/results.html?morphology=on&pageNumber=" + i + "&sortDirection=false&recordsPerPage=_10&showLotsInfoHidden=false&sortBy=UPDATE_DATE&fz44=on&fz223=on&af=on&ca=on&pc=on&pa=on&priceContractAdvantages44IdNameHidden=%7B%7D&priceContractAdvantages94IdNameHidden=%7B%7D&currencyIdGeneral=-1&customerPlace=5277327&delKladrIds=5277327&selectedSubjectsIdNameHidden=%7B%7D&OrderPlacementSmallBusinessSubject=on&OrderPlacementRnpData=on&OrderPlacementExecutionRequirement=on&orderPlacement94_0=0&orderPlacement94_1=0&orderPlacement94_2=0&contractPriceCurrencyId=-1&budgetLevelIdNameHidden=%7B%7D&nonBudgetTypesIdNameHidden=%7B%7D";
//            Document doc = Jsoup.connect(myStr).get();
//            Elements element = doc.select("a");
//            for (Element els : element) {
//                String str = els.attr("abs:href");
//                if ((Pattern.matches(regex1, str) && Pattern.matches(regex2, str)) || (Pattern.matches(regex3, str) && Pattern.matches(regex2, str))) {
//                    refList.add(str);
//                }
//            }
//        }
        for (int i = 1; i <=1; i++) {
            String myStr="https://www.kinopoisk.ru/lists/top250/?page=" + i + "&tab=all";
            Document doc = Jsoup.connect(myStr)
                    .proxy("127.0.0.1", 1080)
                    .get();
            Elements element = doc.select("a");
            for (Element els : element) {
                String str = els.attr("abs:href");
//                try {
//                    //TimeUnit.SECONDS.sleep(1);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                String regex = "https://www.kinopoisk.ru//film/.*/";
                if(Pattern.matches(regex,str)){
                    refList.add(str);
                }
            }
        }
        return refList;
    }
}

