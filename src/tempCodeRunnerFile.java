readFromFileD("Compressed test.txt");
        String k = str1.toString();
        try {
            FileWriter fileWriter = new FileWriter("Decompressed test.txt");
            fileWriter.write(k);

            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }