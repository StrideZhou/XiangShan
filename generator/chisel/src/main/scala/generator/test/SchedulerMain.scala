package generator.test

import org.chipsalliance.cde.config.Parameters
import freechips.rocketchip.diplomacy.LazyModule
import top.{ArgParser, BaseConfig}
import generator.Generator
import xiangshan.backend.issue.Scheduler
import xiangshan.{XSCoreParameters, XSCoreParamsKey}
import xiangshan.backend._

object SchedulerMain extends App {
  val (config, firrtlOpts, firtoolOpts) = ArgParser.parse(args)

  val backendParams = config(XSCoreParamsKey).backendParams

  val schdParams = backendParams.intSchdParams.get
  val schd = LazyModule(new Scheduler(schdParams)(config))

  Generator.execute(
    firrtlOpts,
    schd.module,
    firtoolOpts
  )
}
