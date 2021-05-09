package org.bsm.action;

import org.apache.struts2.convention.annotation.Action;
import org.bsm.service.RepairServiceI;
import org.springframework.beans.factory.annotation.Autowired;

@Action(value = "repairAction")
public class RepairAction extends BaseAction {

    RepairServiceI repairServiceI;

    public RepairServiceI getRepairServiceI() {
        return repairServiceI;
    }

    @Autowired
    public void setRepairServiceI(RepairServiceI repairServiceI) {
        this.repairServiceI = repairServiceI;
    }

    public void init() {
        repairServiceI.repair();
    }
}
