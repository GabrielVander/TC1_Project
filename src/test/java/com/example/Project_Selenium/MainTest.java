package com.example.Project_Selenium;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

enum Option {
  ACTION("Action", 0),
  ADVENTURE("Adventure", 1),
  CLASSICS("Classics", 2),
  COMIC("Comic", 3),
  MYSTERY("Mystery", 4),
  FANTASY("Fantasy", 5),
  HISTORICAL_FICTION("Historical Fiction", 6),
  LITERARY_FICTION("Literary Fiction", 7),
  SCIENCE_FICTION("Science Fiction", 8),
  HORROR("Horror", 9),
  ROMANCE("Romance", 10),
  SUSPENSE("Suspense", 11),
  BIOGRAPHY("Biography", 12),
  REAL_HISTORY("Real History", 13),
  SELF_HELP("Self-help", 14),
  POETRY("Poetry", 15),
  TRUE_CRIME("True Crime", 16);

  private final String value;
  private final Integer index;

  Option(String value, Integer index) {
    this.value = value;
    this.index = index;
  }

  public String getValue() {
    return value;
  }

  public Integer getIndex() {
    return index;
  }
}

public class MainTest {

  private static final WebDriver browser = new FirefoxDriver();
  private static final String baseURL = "http://localhost:8080";

  @BeforeAll
  static void beforeAll() {
    System.setProperty("webdriver.gecko.driver", "c:\\geckodriver.exe");
    browser.get(baseURL);
  }

  @AfterAll
  static void afterAll() {
    browser.quit();
  }

  @AfterEach
  void tearDown() {
    browser.navigate().to(baseURL);
  }

  @Test
  @DisplayName("Main page is accessible")
  public void openPage() {
    assertEquals(baseURL + "/", browser.getCurrentUrl());
  }

  @Nested
  @DisplayName("Links are accessed correctly")
  class Links {

    @Nested
    @DisplayName("Main page's links")
    class Main {

      @Test
      @DisplayName("Books submenu")
      void books() {
        final WebElement booksBtn = browser.findElement(By.id("booksBtn"));
        assertNotNull(booksBtn);

        booksBtn.click();
        assertEquals(baseURL + "/books", browser.getCurrentUrl());
      }

      @Test
      @DisplayName("People submenu")
      void people() {
        final WebElement peopleBtn = browser.findElement(By.id("peopleBtn"));
        assertNotNull(peopleBtn);

        peopleBtn.click();
        assertEquals(baseURL + "/people", browser.getCurrentUrl());
      }

    }

    @Nested
    @DisplayName("Books submenu's links")
    class BooksSubmenu {

      @BeforeEach
      void setUp() {
        browser.findElement(By.id("booksBtn")).click();
      }

      @Test
      @DisplayName("Add new book")
      void add() {
        final WebElement addBtn = browser.findElement(By.id("addBtn"));
        assertNotNull(addBtn);

        addBtn.click();
        assertEquals(baseURL + "/books/add", browser.getCurrentUrl());
      }

      @Test
      @DisplayName("List all books")
      void list() {
        final WebElement listAllBtn = browser.findElement(By.id("listAllBtn"));
        assertNotNull(listAllBtn);

        listAllBtn.click();
        assertEquals(baseURL + "/books/list", browser.getCurrentUrl());
      }

      @Test
      @DisplayName("Edit book")
      void edit() {
        final WebElement editBtn = browser.findElement(By.id("editBtn"));
        assertNotNull(editBtn);

        editBtn.click();
        assertEquals(baseURL + "/books/list", browser.getCurrentUrl());
      }

      @Test
      @DisplayName("Remove book")
      void remove() {
        final WebElement removeBtn = browser.findElement(By.id("removeBtn"));
        assertNotNull(removeBtn);

        removeBtn.click();
        assertEquals(baseURL + "/books/list", browser.getCurrentUrl());
      }

      @Test
      @DisplayName("Homepage")
      void homePage() {
        final WebElement homeBtn = browser.findElement(By.id("homeBtn"));
        assertNotNull(homeBtn);

        homeBtn.click();
        assertEquals(baseURL + "/", browser.getCurrentUrl());
      }

    }

    @Nested
    @DisplayName("People submenu's links")
    class PeopleSubmenu {

      @BeforeEach
      void setUp() {
        browser.findElement(By.id("peopleBtn")).click();
      }

      @Test
      @DisplayName("Add new person")
      void add() {
        final WebElement addBtn = browser.findElement(By.id("addBtn"));
        assertNotNull(addBtn);

        addBtn.click();
        assertEquals(baseURL + "/people/add", browser.getCurrentUrl());
      }

      @Test
      @DisplayName("List all people")
      void list() {
        final WebElement listAllBtn = browser.findElement(By.id("listAllBtn"));
        assertNotNull(listAllBtn);

        listAllBtn.click();
        assertEquals(baseURL + "/people/list", browser.getCurrentUrl());
      }

      @Test
      @DisplayName("Edit person")
      void edit() {
        final WebElement editBtn = browser.findElement(By.id("editBtn"));
        assertNotNull(editBtn);

        editBtn.click();
        assertEquals(baseURL + "/people/list", browser.getCurrentUrl());
      }

      @Test
      @DisplayName("Remove person")
      void remove() {
        final WebElement removeBtn = browser.findElement(By.id("removeBtn"));
        assertNotNull(removeBtn);

        removeBtn.click();
        assertEquals(baseURL + "/people/list", browser.getCurrentUrl());
      }

      @Test
      @DisplayName("Homepage")
      void homePage() {
        final WebElement homeBtn = browser.findElement(By.id("homeBtn"));
        assertNotNull(homeBtn);

        homeBtn.click();
        assertEquals(baseURL + "/", browser.getCurrentUrl());
      }

    }

  }

  @Nested
  @DisplayName("Text inputs are working correctly")
  class TextInputs {

    @Nested
    @DisplayName("Add book form")
    class AddBook {

      @BeforeEach
      void setUp() {
        browser.findElement(By.id("booksBtn")).click();
        browser.findElement(By.id("addBtn")).click();
      }

      @Test
      @DisplayName("ISBN")
      void isbn() {
        WebElement isbnInput = browser.findElement(By.id("isbnInput"));
        assertNotNull(isbnInput);

        assertEquals("", isbnInput.getAttribute("value"));
        isbnInput.sendKeys("ISBN test");
        assertEquals("ISBN test", isbnInput.getAttribute("value"));
      }

      @Test
      @DisplayName("Title")
      void title() {
        final WebElement titleInput = browser.findElement(By.id("titleInput"));
        assertNotNull(titleInput);

        assertEquals("", titleInput.getAttribute("value"));
        titleInput.sendKeys("Title test");
        assertEquals("Title test", titleInput.getAttribute("value"));
      }

      @Test
      @DisplayName("Authors")
      void authors() {
        final WebElement authorsInput = browser.findElement(By.id("authorsInput"));
        assertNotNull(authorsInput);

        assertEquals("", authorsInput.getAttribute("value"));
        authorsInput.sendKeys("Authors test1;test2");
        assertEquals("Authors test1;test2", authorsInput.getAttribute("value"));
      }

      @Test
      @DisplayName("Number of pages")
      void numPages() {
        final WebElement numPagesInput = browser.findElement(By.id("numPagesInput"));
        assertNotNull(numPagesInput);

        assertEquals("", numPagesInput.getAttribute("value"));
        numPagesInput.sendKeys("NumberOfPages test");
        assertEquals("NumberOfPages test", numPagesInput.getAttribute("value"));
      }

    }

    @Nested
    @DisplayName("Add person form")
    class AddPerson {

      @BeforeEach
      void setUp() {
        browser.findElement(By.id("peopleBtn")).click();
        browser.findElement(By.id("addBtn")).click();
      }

      @Test
      @DisplayName("Cpf")
      void cpf() {
        WebElement cpfInput = browser.findElement(By.id("cpfInput"));
        assertNotNull(cpfInput);

        assertEquals("", cpfInput.getAttribute("value"));
        cpfInput.sendKeys("CPF test");
        assertEquals("CPF test", cpfInput.getAttribute("value"));
      }

      @Test
      @DisplayName("Name")
      void name() {
        final WebElement nameInput = browser.findElement(By.id("nameInput"));
        assertNotNull(nameInput);

        assertEquals("", nameInput.getAttribute("value"));
        nameInput.sendKeys("Name test");
        assertEquals("Name test", nameInput.getAttribute("value"));
      }

      @Test
      @DisplayName("Street number")
      void number() {
        final WebElement streetNumberInput = browser.findElement(By.id("streetNumberInput"));
        assertNotNull(streetNumberInput);

        assertEquals("", streetNumberInput.getAttribute("value"));
        streetNumberInput.sendKeys("StreetNumber test");
        assertEquals("StreetNumber test", streetNumberInput.getAttribute("value"));
      }

      @Test
      @DisplayName("ZIP code")
      void zip() {
        final WebElement zipCodeInput = browser.findElement(By.id("zipCodeInput"));
        assertNotNull(zipCodeInput);

        assertEquals("", zipCodeInput.getAttribute("value"));
        zipCodeInput.sendKeys("ZIP test");
        assertEquals("ZIP test", zipCodeInput.getAttribute("value"));
      }

      @Test
      @DisplayName("First email")
      void email1() {
        final WebElement firstEmailInput = browser.findElement(By.id("firstEmailInput"));
        assertNotNull(firstEmailInput);

        assertEquals("", firstEmailInput.getAttribute("value"));
        firstEmailInput.sendKeys("Email1 test");
        assertEquals("Email1 test", firstEmailInput.getAttribute("value"));
      }

      @Test
      @DisplayName("Second email")
      void email2() {
        final WebElement secondEmailInput = browser.findElement(By.id("secondEmailInput"));
        assertNotNull(secondEmailInput);

        assertEquals("", secondEmailInput.getAttribute("value"));
        secondEmailInput.sendKeys("Email2 test");
        assertEquals("Email2 test", secondEmailInput.getAttribute("value"));
      }

      @Test
      @DisplayName("First phone")
      void phone1() {
        final WebElement firstPhoneInput = browser.findElement(By.id("firstPhoneInput"));
        assertNotNull(firstPhoneInput);

        assertEquals("", firstPhoneInput.getAttribute("value"));
        firstPhoneInput.sendKeys("Phone1 test");
        assertEquals("Phone1 test", firstPhoneInput.getAttribute("value"));
      }

      @Test
      @DisplayName("Second phone")
      void phone2() {
        final WebElement secondPhoneInput = browser.findElement(By.id("secondPhoneInput"));
        assertNotNull(secondPhoneInput);

        assertEquals("", secondPhoneInput.getAttribute("value"));
        secondPhoneInput.sendKeys("Phone2 test");
        assertEquals("Phone2 test", secondPhoneInput.getAttribute("value"));
      }

      @Test
      @DisplayName("Occupation")
      void occupation() {
        final WebElement occupationInput = browser.findElement(By.id("occupationInput"));
        assertNotNull(occupationInput);

        assertEquals("", occupationInput.getAttribute("value"));
        occupationInput.sendKeys("Occupation test");
        assertEquals("Occupation test", occupationInput.getAttribute("value"));
      }

    }

  }

  @Nested
  @DisplayName("Select field is working correctly")
  class SelectInput {

    @Nested
    @DisplayName("Genre select")
    class AddBook {

      private Select genreSelect;

      @BeforeEach
      void setUp() {
        browser.findElement(By.id("booksBtn")).click();
        browser.findElement(By.id("addBtn")).click();
        genreSelect = new Select(browser.findElement(By.id("genreSelect")));
      }

      @Test
      @DisplayName("First value is Action")
      void first() {
        assertEquals(
            "Action",
            genreSelect
                .getFirstSelectedOption()
                .getAttribute("value")
        );
      }

      @Test
      @DisplayName("There are 17 options")
      void options() {
        assertEquals(17, genreSelect.getOptions().size());
      }

      @Test
      @DisplayName("All options are present")
      void allOptions() {
        final Object[] expected = Arrays.stream(Option
            .values())
            .map(Option::getValue)
            .toArray();

        final Object[] actual = genreSelect
            .getOptions()
            .stream()
            .map(webElement -> webElement.getAttribute("value"))
            .toArray();

        assertArrayEquals(expected, actual);
      }

      @ParameterizedTest(name = "{0}")
      @DisplayName("Options are in the right order")
      @EnumSource(Option.class)
      void order(Option option) {
        genreSelect.selectByIndex(option.getIndex());

        assertEquals(
            option.getValue(),
            genreSelect
                .getFirstSelectedOption()
                .getAttribute("value")
        );
      }
    }

  }

}
