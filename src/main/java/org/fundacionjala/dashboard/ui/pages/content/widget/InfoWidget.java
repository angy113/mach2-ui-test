package org.fundacionjala.dashboard.ui.pages.content.widget;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import org.fundacionjala.dashboard.ui.pages.AbstractBasePage;
import org.fundacionjala.dashboard.utils.Utils;

/**
 * Manage Project Info Widget page.
 */
public class InfoWidget extends AbstractBasePage implements TypeWidget<Map<String, String>> {

    @FindBy(css = "div.info-fields > div.ui.two.column.centered.grid.aligned.basic.field.segment")
    private WebElement projectInfo;

    @FindBy(css = "button.ui.tiny.circular.basic.icon.option.info.button")
    private WebElement addButton;

    @FindBy(className = "info-fields")
    private WebElement widget;

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, String> getDataFromWidget() {
        List<WebElement> projectInfoHeader = projectInfo.findElements(By.className("selected-name"));
        List<WebElement> projectInfoValues = projectInfo.findElements(By.className("selected-value"));
        Map<String, String> mapResult = new HashMap<>();
        for (int i = 0; i < projectInfoHeader.size(); i++) {
            String key = Utils.replaceSpaceWithUnderscore(projectInfoHeader.get(i).getText());
            String value = projectInfoValues.get(i).getText();
            mapResult.put(key, value);
        }
        return mapResult;
    }

    /**
     * Click on "Add Value" button.
     */
    public void clickAddButton() {
        final int positionX = 50;
        final int positionY = 100;
        wait.until(ExpectedConditions.elementToBeClickable(widget));
        Actions action = new Actions(driver)
                .moveToElement(widget, positionX, positionY)
                .click();
        action.perform();
        wait.until(ExpectedConditions.elementToBeClickable(addButton));
        addButton.click();
    }

    /**
     * Verify if exist element.
     *
     * @return true if exist the element.
     */
    public boolean existAddButton() {
        wait.until(ExpectedConditions.elementToBeClickable(widget));
        return !driver.findElements(
                By.cssSelector("button.ui.tiny.circular.basic.icon.option.info.button")).isEmpty();
    }
}
