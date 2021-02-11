import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        // STANDARTINIAI ĮRAŠAI
//        CreateDefaultDAO defaultDAO = new CreateDefaultDAO();
//        defaultDAO.createDefaultEntry("Mid Back-end developer", 1, 1, "60 000 EUR", "Java, Spring, SQL", "You will work in research and development department.", "Kaunas");
//        defaultDAO.createDefaultEntry("Junior Back-end developer", 4, 1, "30 000 EUR",
//                "Python, Html, CSS, Javacript", "We're looking forward for developer with a oportunity to advance and learn in our fast growing team,", "Tallin");
//        defaultDAO.createDefaultEntry("Mid Level PHP Developer", 3, 1, "70 000 EUR", "Java, Spring, Machine learning skills",
//                "You bring 5+ years of experience as a software developer with a proven track record of building SaaS web applications with complex logic Strong knowledge of modern web frameworks and technologies", "Frankfurt");
//        defaultDAO.createDefaultEntry("Senior Software Engineer", 5, 1, "120 000 EUR",
//                "Ruby, Javascript, HTML, CSS", "We're looking for a well-rounded full stack engineer to help boost growth and brand awareness across GitHub’s various products.", "Helsinki");
        ManipulationDAO crud = new ManipulationDAO();

        System.out.println("Sveiki. Tai darbo skelbimų programa.");
        System.out.println("-----------------");
        System.out.println("Funkcijos:");
        System.out.println("1. Atspausdinti skelbimų sąrašą.");
        System.out.println("2. Pasinaudoti paieška skelbimuose.");
        System.out.println("-----------------");
        System.out.println("Įveskite funkcijos numerį (pvz. '1') kuria norite pasinaudoti ir paspauskite ENTER.");
        Scanner scan = new Scanner(System.in);
        int funkcija = scan.nextInt();
        switch (funkcija) {
            case 1 -> crud.read();
            case 2 -> {
                System.out.println("Įveskite paieškos frazę:");
                String fraze = scan.next();
                crud.search(fraze);
            }
            default -> System.out.println("Neteisingai pasirinkote funkciją.");
        }
        scan.close();
    }
}
