package swervelib.encoders;

import com.ctre.phoenix.sensors.AbsoluteSensorRange;
import com.ctre.phoenix.sensors.CANCoder;
import com.ctre.phoenix.sensors.CANCoderConfiguration;
import com.ctre.phoenix.sensors.SensorInitializationStrategy;
import com.ctre.phoenix.sensors.SensorTimeBase;
import com.ctre.phoenix.sensors.WPI_CANCoder;

/**
 * Swerve Absolute Encoder for CTRE CANCoders.
 */
public class CANCoderSwerve extends SwerveAbsoluteEncoder
{

  /**
   * CANCoder with WPILib sendable and support.
   */
  public CANCoder encoder;

  /**
   * Initialize the CANCoder on the standard CANBus.
   *
   * @param id CAN ID.
   */
  public CANCoderSwerve(int id)
  {
    encoder = new CANCoder(id);
    encoder.configFactoryDefault();
  }

  /**
   * Initialize the CANCoder on the CANivore.
   *
   * @param id     CAN ID.
   * @param canbus CAN bus to initialize it on.
   */
  public CANCoderSwerve(int id, String canbus)
  {
    encoder = new CANCoder(id, canbus);

    encoder.configFactoryDefault();
  }

  /**
   * Reset the encoder to factory defaults.
   */
  @Override
  public void factoryDefault()
  {
    encoder.configFactoryDefault();
  }

  /**
   * Clear sticky faults on the encoder.
   */
  @Override
  public void clearStickyFaults()
  {
    encoder.clearStickyFaults();
  }

  /**
   * Configure the absolute encoder to read from [0, 360) per second.
   *
   * @param inverted Whether the encoder is inverted.
   */
  @Override
  public void configure(boolean inverted)
  {
    CANCoderConfiguration canCoderConfiguration = new CANCoderConfiguration();
    canCoderConfiguration.absoluteSensorRange = AbsoluteSensorRange.Unsigned_0_to_360;
    canCoderConfiguration.sensorDirection = inverted;
    canCoderConfiguration.initializationStrategy = SensorInitializationStrategy.BootToAbsolutePosition;
    canCoderConfiguration.sensorTimeBase = SensorTimeBase.PerSecond;
    encoder.configAllSettings(canCoderConfiguration);
  }

  /**
   * Get the absolute position of the encoder.
   *
   * @return Absolute position in degrees from [0, 360).
   */
  @Override
  public double getAbsolutePosition()
  {
    return encoder.getAbsolutePosition();
  }

  /**
   * Get the instantiated absolute encoder Object.
   *
   * @return Absolute encoder object.
   */
  @Override
  public Object getAbsoluteEncoder()
  {
    return encoder;
  }
}
