package fileOperations;

import inventoryData.InventoryDataItem;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    public List<InventoryDataItem> readFile() throws IOException{
        List<InventoryDataItem> result;
        try (Stream<InventoryDataItem> items = Files.lines(filePath).map(this::parseLine)) {
            result = items.collect(Collectors.toList());
        } catch (IOException e) {
            throw new IOException();
        }
        return result;
    }

    protected abstract InventoryDataItem parseLine(String line);

    public void saveToFile(List<? extends InventoryDataItem> items) {

        Thread thread = new Thread(() -> {
            try {
                writeToFile(items);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        thread.start();
    }

    private synchronized void writeToFile(List<? extends InventoryDataItem> items)
            throws InterruptedException{
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
