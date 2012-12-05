# -*- encoding: utf-8 -*-

Gem::Specification.new do |s|
  s.name = %q{cuke4duke}
  s.version = "0.4.4"

  s.required_rubygems_version = Gem::Requirement.new(">= 0") if s.respond_to? :required_rubygems_version=
  s.authors = ["Aslak Helles\303\270y"]
  s.date = %q{2011-05-12}
  s.default_executable = %q{cuke4duke}
  s.description = %q{Write Cucumber Step Definitions in Java, Scala, Groovy, Rhino Javascript, Clojure or Ioke}
  s.email = %q{cukes@googlegroups.com}
  s.executables = ["cuke4duke"]
  s.extra_rdoc_files = ["LICENCE", "README.textile"]
  s.files = ["lib/.gitignore", "lib/cucumber/class_support/backtrace_filter.rb", "lib/cucumber/class_support/class_language.rb", "lib/cucumber/clj_support/backtrace_filter.rb", "lib/cucumber/clj_support/clj_language.rb", "lib/cucumber/groovy_support/backtrace_filter.rb", "lib/cucumber/groovy_support/groovy_language.rb", "lib/cucumber/ik_support/backtrace_filter.rb", "lib/cucumber/ik_support/ik_language.rb", "lib/cucumber/java_support/java_analyzer.rb", "lib/cucumber/java_support/java_snippet_generator.rb", "lib/cucumber/java_support/java_snippet_generator_spec.rb", "lib/cucumber/js_support/backtrace_filter.rb", "lib/cucumber/js_support/js_language.rb", "lib/cucumber/jvm_support/backtrace_filter.rb", "lib/cucumber/scala_support/backtrace_filter.rb", "lib/cucumber/scala_support/scala_analyzer.rb", "lib/cucumber/scala_support/scala_snippet_generator.rb", "lib/cuke4duke.rb", "lib/cuke4duke/language_proxy.rb", "lib/cuke4duke/py_string_ext.rb", "lib/cuke4duke/scenario_ext.rb", "lib/cuke4duke/step_match_ext.rb", "lib/cuke4duke/step_mother_ext.rb", "lib/cuke4duke/table_ext.rb", "lib/cuke4duke/version.rb", "lib/cuke4duke-0.4.4.jar", "pom.xml", "LICENCE", "README.textile", "bin/cuke4duke"]
  s.homepage = %q{http://cukes.info}
  s.rdoc_options = ["--charset=UTF-8"]
  s.require_paths = ["lib"]
  s.rubygems_version = %q{1.3.6}
  s.summary = %q{cuke4duke-0.4.4}

  if s.respond_to? :specification_version then
    current_version = Gem::Specification::CURRENT_SPECIFICATION_VERSION
    s.specification_version = 3

    if Gem::Version.new(Gem::RubyGemsVersion) >= Gem::Version.new('1.2.0') then
      s.add_runtime_dependency(%q<cucumber>, [">= 0.10.2"])
      s.add_development_dependency(%q<rspec>, [">= 2.5.0"])
      s.add_development_dependency(%q<celerity>, [">= 0.8.9"])
      s.add_development_dependency(%q<jruby-openssl>, [">= 0.7.4"])
      s.add_development_dependency(%q<bundler>, [">= 1.0.13"])
      s.add_development_dependency(%q<rake>, [">= 0.8.7"])
    else
      s.add_dependency(%q<cucumber>, [">= 0.10.2"])
      s.add_dependency(%q<rspec>, [">= 2.5.0"])
      s.add_dependency(%q<celerity>, [">= 0.8.9"])
      s.add_dependency(%q<jruby-openssl>, [">= 0.7.4"])
      s.add_dependency(%q<bundler>, [">= 1.0.13"])
      s.add_dependency(%q<rake>, [">= 0.8.7"])
    end
  else
    s.add_dependency(%q<cucumber>, [">= 0.10.2"])
    s.add_dependency(%q<rspec>, [">= 2.5.0"])
    s.add_dependency(%q<celerity>, [">= 0.8.9"])
    s.add_dependency(%q<jruby-openssl>, [">= 0.7.4"])
    s.add_dependency(%q<bundler>, [">= 1.0.13"])
    s.add_dependency(%q<rake>, [">= 0.8.7"])
  end
end
