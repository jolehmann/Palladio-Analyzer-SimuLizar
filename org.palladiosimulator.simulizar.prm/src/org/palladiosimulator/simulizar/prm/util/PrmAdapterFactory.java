/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.palladiosimulator.simulizar.prm.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;
import org.palladiosimulator.simulizar.prm.PCMModelElementMeasurement;
import org.palladiosimulator.simulizar.prm.PRMModel;
import org.palladiosimulator.simulizar.prm.PrmPackage;
import org.palladiosimulator.simulizar.prm.ResourceContainerMeasurement;
import org.palladiosimulator.simulizar.prm.UniqueElement;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.palladiosimulator.simulizar.prm.PrmPackage
 * @generated
 */
public class PrmAdapterFactory extends AdapterFactoryImpl
{
   /**
     * The cached model package.
     * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
     * @generated
     */
   protected static PrmPackage modelPackage;

   /**
     * Creates an instance of the adapter factory.
     * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
     * @generated
     */
   public PrmAdapterFactory()
   {
        if (modelPackage == null) {
            modelPackage = PrmPackage.eINSTANCE;
        }
    }

   /**
     * Returns whether this factory is applicable for the type of the object.
     * <!-- begin-user-doc -->
    * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
    * <!-- end-user-doc -->
     * @return whether this factory is applicable for the type of the object.
     * @generated
     */
   @Override
   public boolean isFactoryForType(Object object)
   {
        if (object == modelPackage) {
            return true;
        }
        if (object instanceof EObject) {
            return ((EObject)object).eClass().getEPackage() == modelPackage;
        }
        return false;
    }

   /**
     * The switch that delegates to the <code>createXXX</code> methods.
     * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
     * @generated
     */
   protected PrmSwitch<Adapter> modelSwitch =
      new PrmSwitch<Adapter>() {
            @Override
            public Adapter casePRMModel(PRMModel object) {
                return createPRMModelAdapter();
            }
            @Override
            public Adapter casePCMModelElementMeasurement(PCMModelElementMeasurement object) {
                return createPCMModelElementMeasurementAdapter();
            }
            @Override
            public Adapter caseUniqueElement(UniqueElement object) {
                return createUniqueElementAdapter();
            }
            @Override
            public Adapter caseResourceContainerMeasurement(ResourceContainerMeasurement object) {
                return createResourceContainerMeasurementAdapter();
            }
            @Override
            public Adapter defaultCase(EObject object) {
                return createEObjectAdapter();
            }
        };

   /**
     * Creates an adapter for the <code>target</code>.
     * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
     * @param target the object to adapt.
     * @return the adapter for the <code>target</code>.
     * @generated
     */
   @Override
   public Adapter createAdapter(Notifier target)
   {
        return modelSwitch.doSwitch((EObject)target);
    }


   /**
     * Creates a new adapter for an object of class '{@link org.palladiosimulator.simulizar.prm.PRMModel <em>PRM Model</em>}'.
     * <!-- begin-user-doc -->
    * This default implementation returns null so that we can easily ignore cases;
    * it's useful to ignore a case when inheritance will catch all the cases anyway.
    * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.palladiosimulator.simulizar.prm.PRMModel
     * @generated
     */
   public Adapter createPRMModelAdapter()
   {
        return null;
    }

   /**
     * Creates a new adapter for an object of class '{@link org.palladiosimulator.simulizar.prm.PCMModelElementMeasurement <em>PCM Model Element Measurement</em>}'.
     * <!-- begin-user-doc -->
    * This default implementation returns null so that we can easily ignore cases;
    * it's useful to ignore a case when inheritance will catch all the cases anyway.
    * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.palladiosimulator.simulizar.prm.PCMModelElementMeasurement
     * @generated
     */
   public Adapter createPCMModelElementMeasurementAdapter()
   {
        return null;
    }

   /**
     * Creates a new adapter for an object of class '{@link org.palladiosimulator.simulizar.prm.UniqueElement <em>Unique Element</em>}'.
     * <!-- begin-user-doc -->
    * This default implementation returns null so that we can easily ignore cases;
    * it's useful to ignore a case when inheritance will catch all the cases anyway.
    * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.palladiosimulator.simulizar.prm.UniqueElement
     * @generated
     */
   public Adapter createUniqueElementAdapter()
   {
        return null;
    }

   /**
     * Creates a new adapter for an object of class '{@link org.palladiosimulator.simulizar.prm.ResourceContainerMeasurement <em>Resource Container Measurement</em>}'.
     * <!-- begin-user-doc -->
    * This default implementation returns null so that we can easily ignore cases;
    * it's useful to ignore a case when inheritance will catch all the cases anyway.
    * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.palladiosimulator.simulizar.prm.ResourceContainerMeasurement
     * @generated
     */
   public Adapter createResourceContainerMeasurementAdapter()
   {
        return null;
    }

   /**
     * Creates a new adapter for the default case.
     * <!-- begin-user-doc -->
    * This default implementation returns null.
    * <!-- end-user-doc -->
     * @return the new adapter.
     * @generated
     */
   public Adapter createEObjectAdapter()
   {
        return null;
    }

} //PrmAdapterFactory