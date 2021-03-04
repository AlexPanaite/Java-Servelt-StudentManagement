package project.Log;

public class Log {
    private static Log instance = null;
    private int id;
    private String username;
    private String table_name;
    private String table_row;
    private String command;
    private String data;


    public Log() {
    }

    public Log(int id, String username, String table_name, String table_row, String command, String data) {
        this.id = id;
        this.username = username;
        this.table_name = table_name;
        this.table_row = table_row;
        this.command = command;
        this.data = data;
    }

    //important singleton function
    public static Log getInstance() {
        if(instance == null)
            instance = new Log();
        return instance;
    }


    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getTable_name() {
        return table_name;
    }

    public String getTable_row() {
        return table_row;
    }

    public String getCommand() {
        return command;
    }

    public String getData() {
        return data;
    }

    public static void setInstance(Log instance) {
        Log.instance = instance;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setTable_name(String table_name) {
        this.table_name = table_name;
    }

    public void setTable_row(String table_row) {
        this.table_row = table_row;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public void setData(String data) {
        this.data = data;
    }
}
