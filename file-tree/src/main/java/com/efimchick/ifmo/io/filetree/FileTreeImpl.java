package com.efimchick.ifmo.io.filetree;

import java.util.Optional;
import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;



public class FileTreeImpl implements FileTree {

    @Override
    public Optional<String> tree(Path path) {
        File file = new File(String.valueOf(path));
        if (!file.exists()) return Optional.empty();
        if (file.isFile()) {
            return Optional.of(file.getName() + " " + file.length() + " bytes");
        }
        if (file.isDirectory()){
            return Optional.of(dirForTree(file, new ArrayList<>(), 0));
        }
        return Optional.empty();
    }

    public String dirForTree(File fold, List<Boolean> endFolders, int lvl){
        StringBuilder directory = new StringBuilder();
        if (endFolders.size() != 0) {
            for (int i = 0; i < lvl; i++) {
                if (fold.isFile()) {
                    directory.append("│  ");
                }
            }
            directory.append(!(endFolders.get(endFolders.size() - 1)) ? "├─ " : "└─ ");
        }
        directory.append(fold.getName()).append(" ").append(getFoldSize(fold)).append(" bytes");

        File[] files = fold.listFiles();
        assert files != null;

        int count = files.length;
        files = sortFiles(files);


        for (int i = 0; i < count; i++) {
            directory.append("\n");
            for (Boolean lastFolder : endFolders) {
                if (lastFolder) {
                    directory.append("   ");
                } else {
                    directory.append("│  ");
                }
            }
            if (files[i].isFile()) {
                directory.append(i + 1 == count ? "└" : "├").append("─ ").append(files[i].getName()).append(" ").append(files[i].length()).append(" bytes");
            } else {
                ArrayList<Boolean> list = new ArrayList<>(endFolders);
                list.add(i + 1 == count);
                directory.append(dirForTree(files[i], list, lvl + 1));
            }
        }
        return directory.toString();
    }

    private long getFoldSize(File folder) {
        long size = 0;
        File[] files = folder.listFiles();
        assert files != null;

        for (File file : files) {
            if (file.isFile()) {
                size += file.length();
            } else {
                size += getFoldSize(file);
            }
        }
        return size;
    }

    private File[] sortFiles(File[] inData) {

        List<File> files = new ArrayList<>();

        List<File> directories = new ArrayList<>();
        for (File file : inData) {
            if (file.isDirectory()) directories.add(file);
            else files.add(file);
        }

        directories.sort((o1, o2) -> o1.getName().toLowerCase().compareTo(o2.getName().toLowerCase()));
        files.sort((o1, o2) -> o1.getName().toLowerCase().compareTo(o2.getName().toLowerCase()));
        directories.addAll(files);

        return directories.toArray(new File[directories.size()]);
    }
}
