package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupData;
import java.util.List;

public class GroupDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    app.getGroupHelper().gotoGroupPage();
    if (! app.getGroupHelper().isThereAGroup()){
      app.getGroupHelper().createGroup(new GroupData("AutoCreateGroup4", null, null));
    }
  }

  @Test (enabled = false)
  public void testGroupDeletion() {
    app.getGroupHelper().gotoGroupPage();
    if (! app.getGroupHelper().isThereAGroup()){
      app.getGroupHelper().createGroup(new GroupData("AutoCreateGroup4", null, null));
    }
    List<GroupData> before = app.getGroupHelper().getGroupList();
    int index = before.size()-1;
    app.getGroupHelper().selectGroup(index);
    app.getGroupHelper().deleteSelectedGroup();
    app.getGroupHelper().gotoGroupPage();
    List<GroupData> after = app.getGroupHelper().getGroupList();
    Assert.assertEquals(after.size(),before.size()-1);

    before.remove(index);
    Assert.assertEquals(before,after);
  }

}
