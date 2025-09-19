package FileHandlingUtility;
import java.io.*; // for File, FileWriter, BufferedReader
import java.util.Scanner; // for user input
// Main class
public class FileUtility {
// Scanner for user input  
	static Scanner sc = new Scanner(System.in);
// Main method
	public static void main(String[] args) {
		while (true) {
			// Menu options
			System.out.println("\n--- FILE HANDLING MENU ---");
			System.out.println("1. Create / Overwrite file");
			System.out.println("2. Append to file");
			System.out.println("3. Read file");
			System.out.println("4. Delete file");
			System.out.println("5. Rename file");
			System.out.println("6. Replace the text in file");
			System.out.println("0. Exit");
			System.out.print("Choose option: ");
			int choice = sc.nextInt();
			sc.nextLine(); // consume extra newline
			switch (choice) { // switch case is used to allocate each operation to a different integer
			case 1:
				createFile();
				break;
			case 2:
				appendFile();
				break;
			case 3:
				readFile();
				break;
			case 4:
				deleteFile();
				break;
			case 5:
				renameFile();
				break;
			case 6:
				replaceTextInFile();
				break;
			case 0:
				System.out.println("Exiting program...");
				return; // exit from main
			default: // if the choice is invalid
				System.out.println("Invalid choice!");
			}
		}
	}
// Method to create or overwrite a file  
	static void createFile() {
		try {
			System.out.print("Enter file name: ");
			String name = sc.nextLine();
			FileWriter fw = new FileWriter(name); // creates new file or overwrites
			System.out.println("Enter text (type 'END' in new line to finish):");
			while (true) {
				String line = sc.nextLine();
				if (line.equals("END"))
					break; // stop when END entered
				fw.write(line + "\n");
			}
			fw.close();
			System.out.println("File written successfully.");
		} catch (IOException e) {
			System.out.println("Error writing file.");
		}
	}
// Method to append text to an existing file  
	static void appendFile() {
		try {
			System.out.print("Enter file name: ");
			String name = sc.nextLine();
			FileWriter fw = new FileWriter(name, true); // true = append mode
			System.out.println("Enter text to append (type 'END' to finish):");
			while (true) {
				String line = sc.nextLine();
				if (line.equals("END"))
					break; // stop when END entered
				fw.write(line + " ");
			}
			fw.close();
			System.out.println("File appended successfully.");
		} catch (IOException e) {
			System.out.println("Error appending to file.");
		}
	}
// Method to read file contents  
	static void readFile() {
		try {
			System.out.print("Enter file name: ");
			String name = sc.nextLine();
			BufferedReader br = new BufferedReader(new FileReader(name));
			String line;
			System.out.println("\n--- File Content ---");
			while ((line = br.readLine()) != null) {
				System.out.println(line);
			}
			br.close();
		} catch (IOException e) {
			System.out.println("Error reading file.");
		}
	}
// Method to delete a file  
	static void deleteFile() {
		System.out.print("Enter file name: ");
		String name = sc.nextLine();
		File file = new File(name);
		if (file.delete()) {
			System.out.println("File deleted successfully.");
		} else {
			System.out.println("File not found or cannot be deleted.");
		}
	}
// Method to rename a file
	static void renameFile() {
		System.out.print("Enter current file name: ");
		String oldName = sc.nextLine();
		System.out.print("Enter new file name: ");
		String newName = sc.nextLine();
		File oldFile = new File(oldName);
		File newFile = new File(newName);
		if (!oldFile.exists()) {// checking old file existance
			System.out.println("File not found: " + oldName);
			return;// exit from main
		}
		if (newFile.exists()) {// checking new file existance
			System.out.println("A file with the new name already exists: " + newName);
			return;// exit from main
		}
		boolean success = oldFile.renameTo(newFile);
		if (success) {
			System.out.println("File renamed successfully to: " + newName);
		} else {
			System.out.println("Failed to rename file.");
		}
	}
//Method to replace text in a file
	static void replaceTextInFile() {
		System.out.print("Enter file name: ");
		String fileName = sc.nextLine();
		System.out.print("Enter text to find: ");
		String oldText = sc.nextLine();
		System.out.print("Enter replacement text: ");
		String newText = sc.nextLine();
		File file = new File(fileName);
		if (!file.exists()) {// checking file existance
			System.out.println("File not found: " + fileName);
			return;// exit from main
		}
		try {
			// Read full content
			BufferedReader br = new BufferedReader(new FileReader(file));
			StringBuilder content = new StringBuilder();
			String line;
			while ((line = br.readLine()) != null) {
				// Replace old text with new text in each line
				line = line.replace(oldText, newText);
				content.append(line).append("\n");
			}
			br.close();
			// Write updated content back to the file
			FileWriter fw = new FileWriter(file);
			fw.write(content.toString());
			fw.close();
			System.out.println("Text replaced successfully.");
		} catch (IOException e) {
			System.out.println("Error replacing text in file: " + e.getMessage());
		}
	}
}
