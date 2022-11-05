import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    private static void beolvasas(String file) throws IOException{
        fuvarok = new ArrayList<>();
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String dolgok  = br.readLine();
        String sorok = br.readLine();

        while (sorok != null && !sorok.isEmpty()){
            String[] adatok = sorok.replace(",", ".").split(";");
            Fuvar i = new Fuvar(Integer.parseInt(adatok[0]),
                    adatok[1],
                    Integer.parseInt(adatok[2]),
                    Double.parseDouble(adatok[3]),
                    Double.parseDouble(adatok[4]),
                    Double.parseDouble(adatok[5]),
                    adatok[6]);

            fuvarok.add(i);
            sorok = br.readLine();
        }
        br.close();
        fr.close();
    }
    private  static List<Fuvar> fuvarok;

    public static void main(String[] args) {
        String file = "fuvar.csv";
        try {
            beolvasas(file);
            for (Fuvar i: fuvarok) {
                System.out.println(i);
            }
            System.out.println("1.feladat: ");
            System.out.printf("\nAz állományban %d fuvar került feljegyzésre",
                    fuvarok.stream().count());

            System.out.println("2.feladat: ");
            System.out.printf("\n6185 azonosítójú taxisnak %s volt a bevétele ez %d fuvarból állt",fuvarok.stream().filter(fuvarok -> fuvarok.getTaxiAzonosito() == 6185).mapToDouble(Fuvar::getBevetel).sum() ,
                    fuvarok.stream().filter(fuvarok -> fuvarok.getTaxiAzonosito() == 6185).count());

            System.out.println("3.feladat: ");
            System.out.printf("\nÖsszesen %s mérföldet tettek meg a taxisok",fuvarok.stream().mapToDouble(fuvarok -> fuvarok.getMeglettTavolsag()).sum());

            System.out.println("4.feladat: ");
            System.out.printf("\nA leghosszabb fuvar adatai: %s",fuvarok.stream().max(Comparator.comparingInt(Fuvar::getUtazasIdotartama)));

            System.out.println("5.feladat: ");
            System.out.printf("\nA legbőkezűbb borravalójú fuvar adatai: %s",fuvarok.stream().max(Comparator.comparingDouble(Fuvar::getArany)));

            System.out.println("6.feladat: ");
            System.out.printf("\nA 4261 azonosítójú taxis összesen: %s km tett meg.",fuvarok.stream().filter(fuvarok -> fuvarok.getTaxiAzonosito() == 4261).mapToDouble(Fuvar::getMeglettTavolsag).sum()*1.6);

            System.out.println("7.feladat: ");
            //nem tudtam

            System.out.println("8.feladat: ");
            System.out.printf("\nSzerepel-e 1452-es fuvar: %s", fuvarok.stream().anyMatch(fuvarok -> fuvarok.getTaxiAzonosito() == 1452));

            System.out.println("9.feladat: ");
            //nem tudtam

            System.out.println("10.feladat: ");
            System.out.printf("\nDecember 24-én %s fuvar volt.", fuvarok.stream().filter(fuvar -> fuvar.getIndulasIdopont().contains("2016-12-24")).count());

            System.out.println("11.feladat: ");
            //nem tudtam

        }catch (IOException e){
            System.out.printf("\nHiba történt a(z) %s fájl beolvasása során", file);
        }
    }

}