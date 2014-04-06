package com.buildmonkey.terminal.display;

/**
 * Created by v-jborkowski on 05/04/2014.
 */
import com.sun.jna.Native;
import com.sun.jna.Structure;
import com.sun.jna.win32.StdCallLibrary;

import java.util.List;

public class ConsoleInfo {

    public interface Kernel32 extends StdCallLibrary {
        Kernel32 INSTANCE = (Kernel32)
                Native.loadLibrary("kernel32", Kernel32.class);

        int STD_INPUT_HANDLE = -10;
        int STD_OUTPUT_HANDLE = -11;
        int STD_ERROR_HANDLE = -12;

        int GetStdHandle(
                int nStdHandle
        );

        public static class CONSOLE_SCREEN_BUFFER_INFO extends Structure {
            public short wSizeX;  // effective console width
            public short wSizeY;
            public short wCursorPositionX;
            public short wCursorPositionY;
            public short wAttributes;
            public short wWindowLeft;
            public short wWindowTop;
            public short wWindowRight;
            public short wWindowBottom;
            public short wMaximumWindowSizeX;
            public short wMaximumWindowSizeY;

            @Override
            protected List getFieldOrder() {

                // James added this to get rid of errors
                return null;
            }
        }

        boolean GetConsoleScreenBufferInfo(
                int hConsoleOutput,
                CONSOLE_SCREEN_BUFFER_INFO lpConsoleScreenBufferInfo
        );

    }


    public static void main(String[] args) {
        Kernel32 lib = Kernel32.INSTANCE;

        int h = lib.GetStdHandle(Kernel32.STD_OUTPUT_HANDLE);
        Kernel32.CONSOLE_SCREEN_BUFFER_INFO info
                = new Kernel32.CONSOLE_SCREEN_BUFFER_INFO();
        lib.GetConsoleScreenBufferInfo(h, info);

        System.out.printf("size .x=%d .y=%d\n",
                info.wSizeX, info.wSizeY);
        System.out.printf("cursor .x=%d .y=%d\n",
                info.wCursorPositionX, info.wCursorPositionY);
        System.out.printf("window .left=%d .top=%d .right=%d .bottom=%d\n",
                info.wWindowLeft, info.wWindowTop, info.wWindowRight,
                info.wWindowBottom);
        System.out.printf("max-window-size .x=%d .y=%d\n",
                info.wMaximumWindowSizeX, info.wMaximumWindowSizeY);
    }
}
