package br.com.mauroscl.vtatestproducer;

public class WhiteListRule{

  private String client;

  private String regex;

  public String getClient() {
    return client;
  }

  public String getRegex() {
    return regex;
  }

  public void setClient(final String client) {
    this.client = client;
  }

  public void setRegex(final String regex) {
    this.regex = regex;
  }

  @Override
  public String toString() {
    return "WhiteListRule{" + "client='" + client + '\'' + ", regex='" + regex + '\'' + '}';
  }
}
