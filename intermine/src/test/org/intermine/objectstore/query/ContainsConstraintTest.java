package org.flymine.objectstore.query;

import junit.framework.TestCase;

import org.flymine.model.testmodel.Department;
import org.flymine.model.testmodel.Manager;
import org.flymine.model.testmodel.Employee;

public class ContainsConstraintTest extends TestCase {

    private ContainsConstraint constraint;
    private QueryCollectionReference collRef;
    private QueryObjectReference objRef;
    private QueryClass qc1, qc2, qc3;

    public ContainsConstraintTest(String arg1) {
        super(arg1);
    }

    public void setUp() throws Exception {
        qc1 = new QueryClass(Department.class);
        qc2 = new QueryClass(Manager.class);
        qc3 = new QueryClass(Employee.class);
        collRef = new QueryCollectionReference(qc1, "employees");
        objRef = new QueryObjectReference(qc1, "manager");
    }

    public void testNullConstructor1() throws Exception {
        try {
            constraint = new ContainsConstraint(null, ContainsConstraint.CONTAINS, qc3);
            fail("Expected: NullPointerException");
        } catch (NullPointerException e) {
        }
    }

    public void testNullConstructor2() throws Exception {
        try {
            constraint = new ContainsConstraint(collRef, ContainsConstraint.CONTAINS, null);
            fail("Expected: NullPointerException");
        } catch (NullPointerException e) {
        }
    }

    public void testInvalidType() {
        try {
            constraint = new ContainsConstraint(collRef, 234, qc3);
            fail("An IllegalArgumentException should have been thrown");
        } catch (IllegalArgumentException e) {
        }
    }

    public void testIncompatibleTypesReference() throws Exception {
        try {
            // objRef has type Manager, qc3 is type Employee
            constraint = new ContainsConstraint(objRef, ContainsConstraint.CONTAINS, qc3);
            fail("An IllegalArgumentException should have been thrown");
        } catch (IllegalArgumentException e) {
        }
    }

    public void testEquals() throws Exception {
        ContainsConstraint cc1 = new ContainsConstraint(collRef, ContainsConstraint.CONTAINS, qc3);
        ContainsConstraint cc2 = new ContainsConstraint(collRef, ContainsConstraint.CONTAINS, qc3);
        ContainsConstraint cc3 = new ContainsConstraint(objRef, ContainsConstraint.CONTAINS, qc2);
        ContainsConstraint cc4 = new ContainsConstraint(objRef, ContainsConstraint.CONTAINS, qc2, true);

        assertEquals(cc1, cc1);
        assertEquals(cc1, cc2);
        assertTrue("Expected cc1 to not equal cc3:", !cc1.equals(cc3));
        assertTrue("Expected cc3 to not equal cc4:", !cc3.equals(cc4));
    }

        public void testHashCode() throws Exception {
        ContainsConstraint cc1 = new ContainsConstraint(collRef, ContainsConstraint.CONTAINS, qc3);
        ContainsConstraint cc2 = new ContainsConstraint(collRef, ContainsConstraint.CONTAINS, qc3);
        ContainsConstraint cc3 = new ContainsConstraint(objRef, ContainsConstraint.CONTAINS, qc2);
        ContainsConstraint cc4 = new ContainsConstraint(objRef, ContainsConstraint.CONTAINS, qc2, true);

        assertEquals(cc1.hashCode(), cc1.hashCode());
        assertEquals(cc1.hashCode(), cc2.hashCode());
        assertTrue("Expected cc1.hashCode() to not equal cc3.hashCode():", cc1.hashCode() != cc3.hashCode());
        assertTrue("Expected cc3.hashCode() to not equal cc4.hashCode():", cc3.hashCode() != cc4.hashCode());
    }

}

