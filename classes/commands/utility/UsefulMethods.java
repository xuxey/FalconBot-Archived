package com.xuxe.octaveBot.commands.utility;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class UsefulMethods
{
    int textLineCounter(String filename) throws IOException {
        try (InputStream is = new BufferedInputStream(new FileInputStream(filename))) {
            try {
                byte[] c = new byte[1024];
                int readChars = is.read(c);
                if (readChars == -1) {
                    return 0;
                }
                int count = 0;
                while (readChars == 1024) {
                    for (int i = 0; i < 1024; ) {
                        if (c[i++] == '\n') {
                            ++count;
                        }
                    }
                    readChars = is.read(c);
                }

                // count remaining characters
                while (readChars != -1) {
                    for (int i = 0; i < readChars; ++i) {
                        if (c[i] == '\n') {
                            ++count;
                        }
                    }
                    readChars = is.read(c);
                }
                return count == 0 ? 1 : count;
            } finally {
                is.close();
            }
        }
    }
}
