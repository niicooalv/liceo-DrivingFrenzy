package drivingFrenzy.main;

import java.io.IOException;
import java.util.Random;

import drivingFrenzy.race.Section;
import drivingFrenzy.race.StandardIndoorSection;
import drivingFrenzy.race.Track;
import drivingFrenzy.vehicles.Scooter;
import drivingFrenzy.vehicles.Vehicle;
/**
 * @author ismael This is the Control Center. It is the entry point of the
 *         application, where the races are created, vehicles are added...
 */
		public class ControlCenter {

			private final static String USAGE = "El programa genera un número aleatorio de vehículos y secciones de pista y realiza la carrera. \n"
					+ "El programa automáticamente para tras imprimir una línea, esperando a que el usuario pulse enter para continuar.";

			/**
			 * @param minSections secciones minimas
			 * @param maxSections seccines maximas
			 * @param minVehicles vehiculos minimos
			 * @param maxVehicles vehiculos maximos disponibles
			 * @param minVehicleSpeed velocidad minima
			 * @param maxVehicleSpeed velocidad maxima
			 * @param minSectionLenght tamaño de la recta minima
			 * @param maxSectionLength tamaño de la recta maxima
			 * @param minSectionSpeed velocidad de la recta minima
			 * @param maxSectionSpeed velocidad de la recta maxima
			 * @throws IOException -
			 *
			 *
			 * This method creates simple race with StandarIndoorSection sections
			 * and Scooters, with initial random stats
			 */

		private static void simpleRandomRace(int minSections, int maxSections, int minVehicles, int maxVehicles,
											 int minVehicleSpeed, int maxVehicleSpeed, int minSectionLenght, int maxSectionLength, int minSectionSpeed,
											 int maxSectionSpeed) throws IOException {
			// We will need a variable to return random numbers to generate the initial
			// conditions.
			Random random = new Random();

		// first, we randomly decide on the race conditions.

		int numberOfSections = random.nextInt(minSections, maxSections+1);
		int numberOfVehicles = random.nextInt(minVehicles, maxVehicles+1);

		// next, we create the track.
		Section[] sections = new Section[numberOfSections];

		for (int i = 0; i < numberOfSections; i++) {
			sections[i] = new StandardIndoorSection(random.nextInt(minSectionLenght, maxSectionLength+1),
					"una recta sencilla", random.nextInt(minSectionSpeed, maxSectionSpeed+1));
		}

		Track track = new Track(sections);

		// Next, we create some vehicles
		Vehicle[] vehicles = new Vehicle[numberOfVehicles];
		
		for (int i = 0; i < numberOfVehicles; i++) {
			vehicles[i] = new Scooter(i, "un conductor anónimo", 0, random.nextInt(minVehicleSpeed, maxVehicleSpeed),
					"scooter");
		}
		
		start(track, vehicles);
	}
	//Crear una carrera por defecto
	public static void defaultRace() throws IOException {

		//Crear las secciones
		Section[] sections = new Section[5];
		sections[0] = new StandardIndoorSection(1000, "una recta sencilla", 77);
		sections[1] = new StandardIndoorSection(2000, "la recta 2", 88);
		sections[2] = new StandardIndoorSection(3000, "la recta 3", 202);
		sections[3] = new StandardIndoorSection(2000, "la recta 3", 33);
		sections[4] = new StandardIndoorSection(1000, "la recta 3", 152);
		Track track = new Track(sections);
		// Craer los vehiculos
		Vehicle[] vehicles = new Vehicle[3];
		vehicles[0] = new Scooter(1, "Alonso", 0, 333, "Aston Scooter");
		vehicles[1] = new Scooter(2, "Max", 0, 22, "Red Scooter");
		vehicles[2] = new Scooter(3, "Carlos", 0, 201, "Ferrari Scooter");

		start(track, vehicles);



	}
	/**
	 * This method receives a track and a list of cars and it starts a race, showing the results in command line. 
	 * @throws IOException 
	 */
	private static void start(Track track, Vehicle[] vehicles) throws IOException {
		// At the end, who won the race? We should re-order the results. TODO.

		// We will track the total time per vehicle in an array matching positions. However, this should be done differently, with proper Java Objects.
		double[] times = new double[vehicles.length];
		
		// NOW WE START THE RACE!!!! We have to get the times for each vehicle per
		// section, and then the total time.
		nextComment(
				"Bienvenidos a la carrera simple en l�nea recta indoor. Hoy tenemos algunas scooters tratando de realizar el trayecto lo m�s r�pido posible!");
		nextComment("Comencemos con alguna informaci�n sobre la pista:");
		nextComment(track.getDescription());
		nextComment("Tenemos hoy " + vehicles.length + " competidores: ");
		for (Vehicle vehicle : vehicles) {
			nextComment("Con el n�mero " + vehicle.getNumber() + " tenemos un " + vehicle.getDescription()
					+ " pilotado por " + vehicle.getDriver() + ". Este veh�culo alcanza una velocidad m�xima de "
					+ vehicle.getMaxSpeed() + " km/h");
		}
		nextComment("Comienza la carrera!");
		
		for (int i=0; i<vehicles.length; i++) {
			Vehicle vehicle = vehicles[i];
			// for each vehicle, we want to track its total time.
			int currentSectionPosition = 1;
			double totalTime = 0;
			nextComment("El siguiente piloto es " + vehicle.getDriver() + " con el número " + vehicle.getNumber()
					+ ". Se prepara para salir!");
			for (Section section : track.getSections()) {
				nextComment("\tEl siguiente tramo es el n�mero " + currentSectionPosition + ", "
						+ section.getDescription() + " de " + section.getLength()
						+ " metros de longitud, con una velocidad máxima permitida de "
						+ section.getTheoreticalMaxSpeed() + "km/h");
				// The driver modifies the speed based on the section about to enter
				String action = vehicle.adaptSpeed(section);
				double secondsThisSection = section.getLength() / (vehicle.getCurrentSpeed()*1d/1000*3600); 
				nextComment("\t" + action);
				nextComment("\tPasa la sección en " + secondsThisSection + " segundos." );
				totalTime += secondsThisSection;
				nextComment("\tSu tiempo total tras el tramo " + currentSectionPosition + " es de " + timeTo2Decimals(totalTime) + " segundos");
				currentSectionPosition++;
			}
			nextComment("\tFinaliza el recorrido! Su tiempo total es de " + timeTo2Decimals(totalTime) + " segundos.");
			times[i] = totalTime;
		}
		
		// MODIFY THIS to show the results sorted by total time. 
		nextComment("Y acaba la carrera! Los tiempos de los pilotos son: ");
		for (int i = 0; i<vehicles.length - 1; i++) {
			for (int j = i + 1; j< vehicles.length; j++) {
				if (times[i] > times[j]) {
					double timesTemp = times[i];
					times[i] = times[j];
					times[j] = timesTemp;
					Vehicle vehicleTemp = vehicles[i];
					vehicles[i] = vehicles[j];
					vehicles[j] = vehicleTemp;
				}

			}
		}
		for (int i=0; i<vehicles.length;i++) {
			Vehicle vehicle = vehicles[i];
			nextComment("\t" + vehicle.getDriver() + " con el número " + vehicle.getNumber() + " ha hecho un tiempo de " + timeTo2Decimals(times[i]) + " segundos.");
		}
		
	}

	private static String timeTo2Decimals(double time) {
		return 0.01 * Math.round(time*100)+"";
	}
	
	public static void main(String[] args) throws IOException {
		System.out.println(USAGE);
		defaultRace();
		//simpleRandomRace(50, 100, 2, 5, 40, 150, 500, 2000, 70, 150);
	}

	/*
	 * This method is just used as a convenience method to print a comment and await
	 * for the user to click enter to go to the next comment.
	 */
	private static void nextComment(String comment) throws IOException {
		System.out.println(comment);
//		System.in.read();
	}
}




