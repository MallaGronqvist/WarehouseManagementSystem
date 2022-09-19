package utils;

import product.InventoryDataItem;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public abstract class FileHandler {
    private final Path filePath;

    public FileHandler(String filePath) {
        this.filePath = Path.of(filePath);
    }

    public String getFilePath() {
        return filePath.toString();
    }

    public <T extends InventoryDataItem> List<InventoryDataItem> readFile() throws IOException{
        return Files.lines(filePath).map(this::parseLine).collect(Collectors.toList());
    }

    protected abstract  <T extends InventoryDataItem> InventoryDataItem parseLine(String line);

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
}
