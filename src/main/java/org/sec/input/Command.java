package org.sec.input;

import com.beust.jcommander.Parameter;

public class Command {
    @Parameter(names = {"-h", "--help"}, description = "Help Info", help = true)
    public boolean help;

    @Parameter(names = {"-p", "--password"}, description = "Webshell Password", required = true)
    public String password;

    @Parameter(names = {"-u", "--unicode"}, description = "Use Unicode")
    public boolean unicode;

    @Parameter(names = {"-j","--javascript"},description = "Use JavaScript Engine")
    public boolean javascript;
}
