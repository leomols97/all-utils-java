BUILD=build
LATEX=latexmk -recorder -pdf -pdflatex="pdflatex --shell-escape %O %S" \
		-outdir=$(BUILD)
MAIN=ac

code=access_control
ar=$(code).tar.xz

all: ac.pdf $(ar)

preview:
	$(LATEX) -pvc -interaction=nonstopmode $(MAIN).tex

%.pdf: latexmkbuild
	$(LATEX) $(patsubst %.pdf, %.tex, $@)
	ln -fs $(BUILD)/$@

clean:
	rm -rf $(BUILD)
	rm -f $(MAIN)*.pdf $(ar).tar.xz

# This recipe is always made (because it is phony). Because we need to rely on
# latexmk to check for file dependencies.
latexmkbuild:
	mkdir -p $(BUILD)

$(ar):
	tar -cjvf $(ar) $(addprefix $(code)/, README.md pom.xml .gitignore src)


.PHONY: all latexmkbuild
