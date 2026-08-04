<!-- Claude Opus 5 (claude-opus-5) — created 2026-07-11, rewritten 2026-08-05 -->

# Slides

*For further enquiry please contact Akin Kaldiroglu at akin@kaldiroglu.dev*

Two kinds of PDF live here, and the difference matters.

## 1 · Course-deck snapshots — copies, not originals

`00 - …` through `13 - …` are exports of the bootcamp decks, uploaded by hand so a
reader of this repo can follow the course without it.

**They are snapshots.** The decks are *generated*: every one is built by a
`topic_*.py` script in the course project, exported to `decks/PDF/` there, and that
export is the authoritative copy. Nothing in this repo rebuilds them, so a snapshot
here goes stale the moment a deck changes — and it has before.

To refresh, in the course project:

```bash
python3 topic_13_hexagonal.py     # rebuild one deck   (or every topic_*.py)
./to_pdf.sh                       # export all of them to decks/PDF/
```

then upload the files from `decks/PDF/` over the ones here. Replace the whole set
rather than one file, so the snapshot stays internally consistent — decks
cross-reference each other by page number.

## 2 · Separately-authored material

Files with no generator in the course project. These are originals, not copies:

| File | What it is |
|------|------------|
| `Adapter.pdf` | A 79-page deck on the GoF Adapter pattern — the problem, object vs. class adapter, the three pluggable-adapter techniques, consequences, and ports-and-adapters as its main architectural usage |

Name these after their subject rather than a chapter number, so they survive a
chapter being renumbered.

## Not here

`.pptx` and `.key` files. The decks are built from Python — nothing in this course
is authored in a presentation application, and `.gitignore` already excludes
`*.pptx`.
