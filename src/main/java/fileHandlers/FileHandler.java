package fileHandlers;

import inventoryData.InventoryDataItem;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

import static java.nio.file.Files.notExists;

public abstract class FileHandler {
    private final Path filePath;

    public FileHandler(String filePath) throws FileNotFoundException {

        this.filePath = Path.of(filePath);

        checkFile();
    }

    private void checkFile() throws FileNotFoundException {
        if (notExists(this.filePath)) {
            throw new FileNotFoundException(getFilePath());
        }
    }

    public String getFilePath() {
        return filePath.toString();
    }

    public <T extends InventoryDataItem> List<InventoryDataItem> readFile() throws IOException {
        return Files.lines(filePath).map(this::parseLine).collect(Collectors.toList());
    }

    protected abstract <T extends InventoryDataItem> InventoryDataItem parseLine(String line);

/*
    public void saveToFile(List<? extends InventoryDataItem> items){

        // Check if this is the way to lock the file while writing to it.
        Thread thread = new Thread(() -> {
            synchronized (filePath){
                try(BufferedWriter writer = Files.newBufferedWriter(filePath, Charset.forName("UTF-8"))){

                    for (InventoryDataItem item: items) {
                        writer.write(item.getSavable());
                        writer.write(System.getProperty( "line.separator" ));
                    }

                }catch(IOException exception){
                    System.out.println("Couldn't save to file.");
                }
            }
        });

        thread.start();
    }

 */

    public void saveToFile(List<? extends InventoryDataItem> items) {

        // Check if you need to check for interruptions?
        Thread thread = new Thread(() -> writeToFile(items));

        thread.start();
    }

    private synchronized void writeToFile(List<? extends InventoryDataItem> items) {
        try (BufferedWriter writer = Files.newBufferedWriter(filePath, StandardCharsets.UTF_8)) {
            for (InventoryDataItem item : items) {
                writer.write(item.getSavable());
                writer.write(System.getProperty("line.separator"));
            }
        } catch (IOException exception) {
            System.out.println("Couldn't save to file.");
        }
    }
}
