import managers.Organization;
import java.time.LocalDate;

/**
 * SeedData - Populates database with sample data for testing and demonstration
 * Run this once to populate the system with realistic African wildlife organization data
 */
public class SeedData {
    private static Organization org;

    public static void main(String[] args) {
        System.out.println("\n════════════════════════════════════════════════════════");
        System.out.println("   PAWET-B DATABASE SEEDING");
        System.out.println("   Populating with Sample Data...");
        System.out.println("════════════════════════════════════════════════════════\n");

        org = new Organization();

        seedDepartments();
        seedManagers();
        seedScientists();
        seedTechnicians();
        seedProjects();
        seedEquipment();

        System.out.println("\n════════════════════════════════════════════════════════");
        System.out.println("   ✓ DATABASE SEEDING COMPLETE!");
        System.out.println("════════════════════════════════════════════════════════");
        System.out.println("\nSummary:");
        System.out.println("  ✓ 5 Departments created");
        System.out.println("  ✓ 5 Managers added");
        System.out.println("  ✓ 10 Scientists added");
        System.out.println("  ✓ 7 Technicians added");
        System.out.println("  ✓ 8 Projects created");
        System.out.println("  ✓ 12 Equipment items added");
        System.out.println("\nTotal: 22 Employees, 5 Departments, 8 Projects, 12 Equipment\n");
        System.out.println("Run './run.sh' to start using the system with populated data!\n");
    }

    private static void seedDepartments() {
        System.out.println("→ Creating departments...");

        org.addDepartment("D001", "Marine Conservation", "Coastal Region", 1000000.0, null);
        org.addDepartment("D002", "Wildlife Research", "Savannah Region", 1500000.0, null);
        org.addDepartment("D003", "Forestry Management", "Forest Region", 1200000.0, null);
        org.addDepartment("D004", "Tourism Development", "Central HQ", 800000.0, null);
        org.addDepartment("D005", "Data Analytics", "Central HQ", 600000.0, null);

        System.out.println("  ✓ 5 departments created\n");
    }

    private static void seedManagers() {
        System.out.println("→ Adding managers...");

        org.addManager("M001", "Alice Mbeki", "alice.mbeki@pawet.org", "+254-700-123456",
                "D001", "Department Head", 85000.0, LocalDate.of(2024, 1, 15), 500000.0);

        org.addManager("M002", "David Osei", "david.osei@pawet.org", "+254-700-123457",
                "D002", "Department Head", 90000.0, LocalDate.of(2024, 1, 10), 600000.0);

        org.addManager("M003", "Fatima Hassan", "fatima.hassan@pawet.org", "+254-700-123458",
                "D003", "Department Head", 88000.0, LocalDate.of(2024, 1, 12), 550000.0);

        org.addManager("M004", "Joseph Ndlovu", "joseph.ndlovu@pawet.org", "+254-700-123459",
                "D004", "Department Head", 82000.0, LocalDate.of(2024, 1, 20), 400000.0);

        org.addManager("M005", "Sarah Kamau", "sarah.kamau@pawet.org", "+254-700-123460",
                "D005", "Department Head", 95000.0, LocalDate.of(2024, 1, 8), 300000.0);

        System.out.println("  ✓ 5 managers added\n");
    }

    private static void seedScientists() {
        System.out.println("→ Adding scientists...");

        org.addScientist("S001", "Kofi Annan", "kofi.annan@pawet.org", "+254-700-200001",
                "D001", "Senior Marine Biologist", 70000.0, LocalDate.of(2024, 2, 1), "Coral Reef Ecology");

        org.addScientist("S002", "Thandiwe Moyo", "thandiwe.moyo@pawet.org", "+254-700-200002",
                "D001", "Marine Researcher", 68000.0, LocalDate.of(2024, 2, 15), "Coral Ecology");

        org.addScientist("S003", "Omar Ali", "omar.ali@pawet.org", "+254-700-200003",
                "D002", "Senior Wildlife Biologist", 75000.0, LocalDate.of(2024, 2, 5), "Wildlife Behavior");

        org.addScientist("S004", "Grace Mwangi", "grace.mwangi@pawet.org", "+254-700-200004",
                "D002", "Wildlife Ecologist", 72000.0, LocalDate.of(2024, 2, 10), "Savannah Ecology");

        org.addScientist("S005", "Ibrahim Diop", "ibrahim.diop@pawet.org", "+254-700-200005",
                "D002", "Conservation Biologist", 70000.0, LocalDate.of(2024, 2, 20), "Conservation Biology");

        org.addScientist("S006", "Amina Yusuf", "amina.yusuf@pawet.org", "+254-700-200006",
                "D003", "Senior Forestry Scientist", 71000.0, LocalDate.of(2024, 3, 1), "Forestry Science");

        org.addScientist("S007", "Chinwe Okeke", "chinwe.okeke@pawet.org", "+254-700-200007",
                "D003", "Botanist", 69000.0, LocalDate.of(2024, 3, 5), "Forest Botany");

        org.addScientist("S008", "Makena Odhiambo", "makena.odhiambo@pawet.org", "+254-700-200008",
                "D004", "Tourism Ecologist", 66000.0, LocalDate.of(2024, 3, 10), "Tourism Ecology");

        org.addScientist("S009", "Zuri Banda", "zuri.banda@pawet.org", "+254-700-200009",
                "D005", "Senior Data Scientist", 78000.0, LocalDate.of(2024, 3, 15), "Environmental Data Science");

        org.addScientist("S010", "Kwame Asante", "kwame.asante@pawet.org", "+254-700-200010",
                "D005", "GIS Specialist", 76000.0, LocalDate.of(2024, 3, 20), "GIS & Remote Sensing");

        System.out.println("  ✓ 10 scientists added\n");
    }

    private static void seedTechnicians() {
        System.out.println("→ Adding technicians...");

        org.addTechnician("T001", "Amara Okafor", "amara.okafor@pawet.org", "+254-700-300001",
                "D001", "Field Technician", 45000.0, LocalDate.of(2024, 4, 1), "Scuba Diving, Boat Operation, First Aid");

        org.addTechnician("T002", "Lesedi Nkosi", "lesedi.nkosi@pawet.org", "+254-700-300002",
                "D001", "Lab Technician", 43000.0, LocalDate.of(2024, 4, 5), "Water Testing, Navigation, Sample Collection");

        org.addTechnician("T003", "Jabari Mutua", "jabari.mutua@pawet.org", "+254-700-300003",
                "D002", "Field Technician", 46000.0, LocalDate.of(2024, 4, 10), "GPS Operation, Camera Traps, Wildlife Tracking");

        org.addTechnician("T004", "Nia Adeyemi", "nia.adeyemi@pawet.org", "+254-700-300004",
                "D002", "Research Technician", 44000.0, LocalDate.of(2024, 4, 15), "Drone Operation, Animal Tracking, Data Collection");

        org.addTechnician("T005", "Sekou Traore", "sekou.traore@pawet.org", "+254-700-300005",
                "D003", "Forestry Technician", 45000.0, LocalDate.of(2024, 5, 1), "Chainsaw Operation, Heavy Equipment, Tree Identification");

        org.addTechnician("T006", "Asha Mohamed", "asha.mohamed@pawet.org", "+254-700-300006",
                "D004", "Tourism Technician", 42000.0, LocalDate.of(2024, 5, 5), "Photography, Tour Guiding, Customer Service");

        org.addTechnician("T007", "Tendai Mugabe", "tendai.mugabe@pawet.org", "+254-700-300007",
                "D005", "IT Technician", 48000.0, LocalDate.of(2024, 5, 10), "Database Management, System Administration, Network Setup");

        System.out.println("  ✓ 7 technicians added\n");
    }

    private static void seedProjects() {
        System.out.println("→ Creating projects...");

        org.addProject("P001", "Coral Reef Restoration Initiative",
                "Large-scale coral reef restoration in coastal areas", "D001", "S001",
                250000.0, LocalDate.of(2024, 3, 1), LocalDate.of(2025, 2, 28));

        org.addProject("P002", "Marine Biodiversity Survey",
                "Comprehensive survey of marine species diversity", "D001", "S002",
                180000.0, LocalDate.of(2024, 4, 1), LocalDate.of(2024, 12, 31));

        org.addProject("P003", "Lion Population Dynamics Study",
                "Multi-year study of lion populations and behavior", "D002", "S003",
                320000.0, LocalDate.of(2024, 1, 15), LocalDate.of(2025, 12, 31));

        org.addProject("P004", "Elephant Migration Tracking",
                "GPS tracking of elephant herds across savannah", "D002", "S004",
                280000.0, LocalDate.of(2024, 2, 1), LocalDate.of(2025, 1, 31));

        org.addProject("P005", "Indigenous Forest Conservation",
                "Protection and restoration of indigenous forests", "D003", "S006",
                350000.0, LocalDate.of(2024, 1, 10), LocalDate.of(2026, 1, 10));

        org.addProject("P006", "Sustainable Tourism Development",
                "Eco-tourism infrastructure and community programs", "D004", "S008",
                220000.0, LocalDate.of(2024, 3, 15), LocalDate.of(2025, 3, 14));

        org.addProject("P007", "Wildlife Crime Data Analysis",
                "Big data analytics for anti-poaching efforts", "D005", "S009",
                150000.0, LocalDate.of(2024, 4, 1), LocalDate.of(2024, 10, 31));

        org.addProject("P008", "Remote Sensing Habitat Mapping",
                "Satellite imagery for habitat conservation planning", "D005", "S010",
                190000.0, LocalDate.of(2024, 2, 15), LocalDate.of(2025, 2, 14));

        System.out.println("  ✓ 8 projects created\n");
    }

    private static void seedEquipment() {
        System.out.println("→ Adding equipment...");

        org.addEquipment("E001", "Research Vessel Alpha", "Boat", "RV-2024-001",
                "D001", LocalDate.of(2023, 6, 15));

        org.addEquipment("E002", "Underwater Camera System", "Camera", "CAM-UW-445",
                "D001", LocalDate.of(2023, 8, 20));

        org.addEquipment("E003", "GPS Tracking Collar Set", "Tracking", "GPS-COL-2024",
                "D002", LocalDate.of(2024, 1, 10));

        org.addEquipment("E004", "DJI Mavic 3 Drone", "Drone", "DJI-M3-5521",
                "D002", LocalDate.of(2024, 2, 5));

        org.addEquipment("E005", "Thermal Imaging Camera", "Camera", "FLIR-TH-998",
                "D002", LocalDate.of(2023, 11, 15));

        org.addEquipment("E006", "Chainsaw - Stihl MS 462", "Power Tool", "ST-MS462-2024",
                "D003", LocalDate.of(2024, 1, 20));

        org.addEquipment("E007", "Forest Surveying Total Station", "Surveying", "TS-FOR-889",
                "D003", LocalDate.of(2023, 9, 10));

        org.addEquipment("E008", "Canon EOS R5 Camera Kit", "Camera", "CAN-R5-3344",
                "D004", LocalDate.of(2024, 3, 1));

        org.addEquipment("E009", "High-Performance Workstation", "Computer", "HP-Z8-9912",
                "D005", LocalDate.of(2024, 1, 5));

        org.addEquipment("E010", "Data Storage Array 50TB", "Storage", "SAN-50TB-2024",
                "D005", LocalDate.of(2024, 1, 15));

        org.addEquipment("E011", "Portable Water Testing Kit", "Lab Equipment", "WTK-PORT-556",
                "D001", LocalDate.of(2023, 12, 1));

        org.addEquipment("E012", "Satellite Phone System", "Communication", "IRID-SAT-2024",
                "D002", LocalDate.of(2024, 2, 20));

        System.out.println("  ✓ 12 equipment items added\n");
    }
}
