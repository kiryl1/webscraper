package webscrape;


public class Main {
    public static void main(String[] args) {
        //works for Canada Computers only currently
        RTX rxt3080 = new RTX( "https://www.canadacomputers.com/search/results_details.php?language=en&keywords=rtx&cpath=43&specification_43_3=5");
        rxt3080.start();
        RTX rtx3070 = new RTX( "https://www.canadacomputers.com/search/results_details.php?language=en&keywords=rtx&cpath=43&specification_43_3=7");
        rtx3070.start();
        RTX rtx3060ti = new RTX("https://www.canadacomputers.com/search/results_details.php?language=en&keywords=rtx&cpath=43&specification_43_3=8");
        rtx3060ti.start();
        RTX amd = new RTX("https://www.canadacomputers.com/index.php?cPath=43&sf=:3_29,3_30,3_31&mfr=&pr=");
        amd.start();


    }
}